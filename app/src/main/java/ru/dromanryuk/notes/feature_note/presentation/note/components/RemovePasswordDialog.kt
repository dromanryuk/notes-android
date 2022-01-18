package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialog

@Composable
fun RemovePasswordDialog(
    onDismiss: () -> Unit,
    onRemoveNotePassword: () -> Unit
) {
    DefaultDialog(
        onDismissRequest = onDismiss,
        shapeRadius = 5
    ) {
        RemovePasswordDialogContent(
            onDismiss = onDismiss,
            onRemoveNotePassword
        )
    }
}

@Composable
fun RemovePasswordDialogContent(
    onDismiss: () -> Unit,
    onRemoveNotePassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Вы хотите удалить пароль? ",
            color = MaterialTheme.colors.surface,
            maxLines = 1,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(
                    text = "Отмена",
                    color = MaterialTheme.colors.surface
                )
            }
            TextButton(
                onClick = {
                    onRemoveNotePassword()
                    onDismiss()
                }
            ) {
                Text(
                    text = "Удалить",
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}