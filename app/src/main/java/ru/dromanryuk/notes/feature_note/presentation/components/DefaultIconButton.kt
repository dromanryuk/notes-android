package ru.dromanryuk.notes.feature_note.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultIconButton(
    image: ImageVector,
    color: Color,
    function: () -> Unit
) {
    IconButton(
        onClick = function
    ) {
        Icon(
            imageVector = image,
            contentDescription = "",
            tint = color
        )
    }
}