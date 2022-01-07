package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.NotificationAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultIconButton
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun NoteTopAppBar(
    favouriteState: Boolean,
    onNavigateBack: () -> Unit,
    onToggleFavourite: () -> Unit
) {
    TopAppBar(
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
            DefaultIconButton(
                image = Icons.Default.ArrowBack,
                color = MaterialTheme.colors.surface
            ) { onNavigateBack() }
            Row {
                FavouriteIcon(favouriteState) { onToggleFavourite() }
                DefaultIconButton(
                    image = Icons.Outlined.NotificationAdd,
                    color = MaterialTheme.colors.surface
                ) {  }
            }
        }
    }
}

@Composable
private fun FavouriteIcon(isFavourite: Boolean, onToggleFavourite: () -> Unit) {
    val icon = Icons.Default.run { if (isFavourite) BookmarkAdded else BookmarkBorder }
    DefaultIconButton(
        image = icon,
        color = MaterialTheme.colors.surface
    ) { onToggleFavourite() }
}

@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        NoteTopAppBar(false, {}, {})
    }
}