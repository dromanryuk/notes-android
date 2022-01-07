package ru.dromanryuk.notes.feature_note.data.repository

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.itextpdf.text.Document
import com.itextpdf.text.Font
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import ru.dromanryuk.notes.BuildConfig
import ru.dromanryuk.notes.feature_note.domain.repository.NoteShareRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class NoteShareRepositoryImpl @Inject constructor(
    private val context: Context
) : NoteShareRepository {
    override fun createShareFile(content: String, title: String): Uri {
        val font = createFont()
        val newFile = File(context.getExternalFilesDir(null), "$title.pdf")
        val outputStream = FileOutputStream(newFile)
        writeDocument(outputStream, font, content)
        return getUri(newFile, context)
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
}