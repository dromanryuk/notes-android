package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.overview.model.NoteViewState
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalFoundationApi
@Composable
fun NoteCard(
    note: NoteViewState,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = note.title,
                color = MaterialTheme.colors.surface,
                maxLines = 1,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                color = MaterialTheme.colors.surface,
                maxLines = 10,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun PreviewNoteCard() {
    NotesTheme {
        NoteCard(
            note = NoteViewState(
                0,
                "title",
                "content"),
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onLongClick = {  },
                    onClick = {  }
                ),
        )
    }
}