package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OverviewFloatingActionButton(
    onAddClick: () -> Unit,
    navigateToNote: (Int) -> Unit
) {

    val btnGradient = Brush.verticalGradient(
        listOf(
            MaterialTheme.colors.secondary,
            Color.Red
        )
    )

    FloatingActionButton(
        modifier = Modifier.padding(5.dp),
        onClick = {
            onAddClick()
            //navigateToNote()
        },
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(50)
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "",
            tint = MaterialTheme.colors.secondary
        )
    }
}

