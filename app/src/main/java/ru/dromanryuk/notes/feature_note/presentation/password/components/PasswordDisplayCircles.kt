package ru.dromanryuk.notes.feature_note.presentation.password.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PasswordDisplayCircles(password: String, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PasswordCircuits(password)
    }
}

@Composable
private fun PasswordCircuits(password: String) {
    repeat(password.length) {
        PasswordCircuit(canvasColor = MaterialTheme.colors.secondary)
    }
    for (i in 1..(4 - password.length)) {
        PasswordCircuit(canvasColor = MaterialTheme.colors.primary)
    }
}

@Composable
private fun PasswordCircuit(canvasColor: Color) {
    Canvas(
        modifier = Modifier
            .size(30.dp)
            .padding(end = 7.5.dp, start = 7.5.dp),
        onDraw = {
            drawCircle(color = canvasColor)
        }
    )
}

@Composable
@Preview
fun PreviewPasswordDisplayCircles() {
    PasswordDisplayCircles("", Modifier)
}