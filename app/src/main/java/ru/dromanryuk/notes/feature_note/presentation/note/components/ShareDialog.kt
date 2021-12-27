package ru.dromanryuk.notes.feature_note.presentation.note.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteShareType
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialog

@ExperimentalMaterialApi
@Composable
fun ShareDialog(
    onDismiss: () -> Unit,
    onChangeShareType: (NoteShareType) -> Unit,
    hideBottomSheet: () -> Unit
) {
    hideBottomSheet()
    DefaultDialog(
        onDismissRequest = onDismiss,
        shapeRadius = 20
    ) {
        ShareDialogContent(
            changeShareType = onChangeShareType,
            onDismiss = onDismiss,
        )
    }
}

@Composable
fun ShareDialogContent(
    changeShareType: (NoteShareType) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        NoteShareType.values().forEach {
            ShareItem(
                getShareTypeIcon(it),
                getShareTypeTitle(it)
            ) {
                changeShareType(it)
                onDismiss()
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

private fun getShareTypeTitle(action: NoteShareType) =
    when(action) {
        NoteShareType.Text -> "Скопировать текст"
        NoteShareType.Pdf -> "Отправить PDF файлом"
    }

private fun getShareTypeIcon(action: NoteShareType) =
    when(action) {
        NoteShareType.Text -> Icons.Outlined.TextSnippet
        NoteShareType.Pdf -> Icons.Outlined.UploadFile
    }