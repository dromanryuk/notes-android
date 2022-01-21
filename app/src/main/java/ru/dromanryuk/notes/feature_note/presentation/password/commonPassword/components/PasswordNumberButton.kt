package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PasswordNumberButton(
    digit: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = CircleShape,
        onClick = { onClick() }
    ) {
        Text(
            text = digit,
            fontSize = 30.sp,
            color = MaterialTheme.colors.surface
        )
    }
}