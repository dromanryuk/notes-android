package ru.dromanryuk.notes.feature_note.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun DefaultDialog(
    onDismissRequest: () -> Unit,
    shapeRadius: Int,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(shapeRadius)
        ) {
            content()
        }
    }
}