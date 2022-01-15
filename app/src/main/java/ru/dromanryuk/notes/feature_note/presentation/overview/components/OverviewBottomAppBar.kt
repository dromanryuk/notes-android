package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultIconButton
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun OverviewBottomAppBar(
    onAddChecklistNoteClick: () -> Unit,
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        cutoutShape = RoundedCornerShape(50)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DefaultIconButton(
                image = Icons.Outlined.CheckBox,
                color = MaterialTheme.colors.surface
            ) {
                onAddChecklistNoteClick()
            }
            DefaultIconButton(
                image = Icons.Outlined.BookmarkBorder,
                color = MaterialTheme.colors.surface) {}
        }
    }
}

@Preview
@Composable
private fun OverviewBottomAppBarPreview() {
    NotesTheme {
        OverviewBottomAppBar({})
    }
}