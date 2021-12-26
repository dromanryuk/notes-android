package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.note.model.ModalBottomSheetAction

@Composable
fun BottomSheetContent(
    bottomSheetEvent: (ModalBottomSheetAction) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary),
    ) {
        ModalBottomSheetAction.values().forEach {
            BottomSheetListItem(getActionTypeIcon(it), getActionTypeTitle(it)) { bottomSheetEvent(it) }
        }
    }
}

@Composable
private fun BottomSheetListItem(
    image: ImageVector,
    title: String,
    bottomSheetEvent: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .clickable { bottomSheetEvent() },
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

private fun getActionTypeTitle(action: ModalBottomSheetAction) =
    when(action) {
        ModalBottomSheetAction.ShareNote -> "Отправить"
        ModalBottomSheetAction.DeleteNote -> "Удалить"
        ModalBottomSheetAction.CopyNote -> "Создать копию"
        ModalBottomSheetAction.AddPassword -> "Добавить пароль"
    }

private fun getActionTypeIcon(action: ModalBottomSheetAction) =
    when(action) {
        ModalBottomSheetAction.ShareNote -> Icons.Outlined.Link
        ModalBottomSheetAction.DeleteNote -> Icons.Outlined.Delete
        ModalBottomSheetAction.CopyNote -> Icons.Outlined.CopyAll
        ModalBottomSheetAction.AddPassword -> Icons.Outlined.Lock
    }