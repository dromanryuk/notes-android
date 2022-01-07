package ru.dromanryuk.notes.feature_note.presentation.note.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TextSnippet
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteShareType
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialog

@ExperimentalMaterialApi
@Composable
fun ShareDialog(
    content: String,
    onDismiss: () -> Unit,
    hideBottomSheet: () -> Unit,
    uri: Uri
) {
    hideBottomSheet()
    DefaultDialog(
        onDismissRequest = onDismiss,
        shapeRadius = 20
    ) {
        ShareDialogContent(
            content,
            onDismiss = onDismiss,
            uri
        )
    }
}

@Composable
fun ShareDialogContent(
    content: String,
    onDismiss: () -> Unit,
    uri: Uri
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
    ) {
        NoteShareType.values().forEach {
            ShareItem(
                getShareTypeIcon(it),
                getShareTypeTitle(it)
            ) {
                onDismiss()
                shareNote(it, content, uri, context)
            }
        }
    }
}

@Composable
fun ShareItem(
    image: ImageVector,
    title: String,
    shareEvent: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { shareEvent() }
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.padding(15.dp),
            imageVector = image,
            contentDescription = "",
            tint = MaterialTheme.colors.onPrimary
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = title,
            color = MaterialTheme.colors.surface,
            fontSize = 16.sp,
        )
    }
}

private fun getShareTypeIcon(action: NoteShareType) =
    when(action) {
        NoteShareType.Text -> Icons.Outlined.TextSnippet
        NoteShareType.Pdf -> Icons.Outlined.UploadFile
    }

private fun getShareTypeTitle(action: NoteShareType) =
    when(action) {
        NoteShareType.Text -> "Скопировать текст"
        NoteShareType.Pdf -> "Отправить PDF файлом"
    }

private fun shareNote(shareType: NoteShareType, content: String, uri: Uri, context: Context) {
    when (shareType) {
        NoteShareType.Text -> {
            shareTextCopy(content, context)
        }
        NoteShareType.Pdf -> {
            sharePdfFile(uri, context)
        }
    }
}

private fun shareTextCopy(content: String, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, content)
    ContextCompat.startActivity(
        context,
        Intent.createChooser(shareIntent, ""),
        null
    )
}

private fun sharePdfFile(uri: Uri, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "application/pdf"
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    ContextCompat.startActivity(
        context,
        Intent.createChooser(shareIntent, ""),
        null
    )
}
