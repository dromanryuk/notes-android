package ru.dromanryuk.notes.feature_note.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DefaultDialogButtons(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal,
    confirmButtonText: String,
    dismissButtonText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = { onDismissClick() }
        ) {
            Text(
                text = dismissButtonText,
                color = MaterialTheme.colors.surface
            )
        }
        TextButton(
            onClick = {
                onConfirmClick()
            }
        ) {
            Text(
                text = confirmButtonText,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}