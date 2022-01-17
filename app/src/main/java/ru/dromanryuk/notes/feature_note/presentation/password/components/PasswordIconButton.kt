package ru.dromanryuk.notes.feature_note.presentation.password.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun PasswordIconButton(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = CircleShape,
        onClick = { onClick() },
    ){
        Icon(
            icon,
            contentDescription = "",
            tint = MaterialTheme.colors.surface
        )
    }
}