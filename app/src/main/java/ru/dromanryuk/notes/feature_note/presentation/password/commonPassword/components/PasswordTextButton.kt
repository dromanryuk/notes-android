package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PasswordTextButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = CircleShape,
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.surface
        )
    }
}