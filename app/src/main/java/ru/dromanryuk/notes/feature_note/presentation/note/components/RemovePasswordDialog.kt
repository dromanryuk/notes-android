package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialog
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialogButtons
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialogTitle

@Composable
fun RemovePasswordDialog(
    onDismiss: () -> Unit,
    onRemoveNotePassword: () -> Unit,
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
    onRemoveNotePassword: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        DefaultDialogTitle("Вы хотите удалить пароль?", 16.sp)
        DefaultDialogButtons(
            arrangement = Arrangement.Center,
            confirmButtonText = "Удалить",
            dismissButtonText = "Отмена",
            onConfirmClick = {
                onRemoveNotePassword()
                onDismiss()
            },
            onDismissClick = { onDismiss() }
        )
    }
}