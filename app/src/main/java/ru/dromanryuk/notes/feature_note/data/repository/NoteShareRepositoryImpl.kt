package ru.dromanryuk.notes.feature_note.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.itextpdf.text.Document
import com.itextpdf.text.Font
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import ru.dromanryuk.notes.BuildConfig
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import ru.dromanryuk.notes.feature_note.domain.model.toText
import ru.dromanryuk.notes.feature_note.domain.repository.NoteShareRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class NoteShareRepositoryImpl @Inject constructor(
//    private val context: Context
) : NoteShareRepository {

    override fun shareTextCopy(content: String, context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
//        val intent = Intent()
//        val shareIntent = intent.get
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, content)
        ContextCompat.startActivity(
            context,
            Intent.createChooser(shareIntent, "Share with:"),
            null
        )
    }

    private fun convertNoteContentToText(content: NoteContent): String {
        return when(content) {
            is NoteContent.TextNote -> content.text
            is NoteContent.ChecklistNote -> content.toText()
        }
    }

    override fun sharePdfFile(content: String, title: String, context: Context) {
        //val context = LocalContext.current
        println("==============================================================================================================")
        val font = createFont()
        val newFile = File(context.getExternalFilesDir(null), "$title.pdf")
        val outputStream = FileOutputStream(newFile)
        writeDocument(outputStream, font, content)
        val uri = getUri(newFile, context)
        pdfIntent(uri, context)
    }

    private fun createFont(): Font {
        val font = "res/font/roboto.ttf"
        val bf = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
        return Font(bf, 14f, Font.NORMAL)
    }

    private fun writeDocument(outputStream: FileOutputStream, font: Font, content: String) {
        val doc = Document()
        val writer = PdfWriter.getInstance(doc, outputStream)
        doc.open()
        doc.add(com.itextpdf.text.Paragraph(content, font))
        doc.close()
        writer.close()
    }

    private fun getUri(newFile: File, context: Context): Uri {
        return FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider",
            newFile
        )
    }

    private fun pdfIntent(uri: Uri, context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/pdf"
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        ContextCompat.startActivity(
            context,
            Intent.createChooser(shareIntent, "Share with:"),
            null
        )
    }
}