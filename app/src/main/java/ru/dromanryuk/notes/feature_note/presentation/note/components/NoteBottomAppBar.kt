package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultIconButton
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalMaterialApi
@Composable
fun NoteBottomAppBar() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            Row {
                DefaultIconButton(
                    image = Icons.Outlined.AddBox,
                    color = MaterialTheme.colors.surface
                ) { }
                DefaultIconButton(
                    image = Icons.Outlined.Palette,
                    color = MaterialTheme.colors.surface
                ) { }
            }
            Text(
                text = "Последнее изменение: 13:00",
                color = MaterialTheme.colors.surface,
                maxLines = 1,
                fontSize = 14.sp,
            )
            DefaultIconButton(
                image = Icons.Default.MoreVert,
                color = MaterialTheme.colors.surface
            ) { }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        NoteBottomAppBar()
    }
}