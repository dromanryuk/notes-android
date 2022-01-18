package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dromanryuk.notes.feature_note.presentation.overview.model.NoteViewState

@ExperimentalFoundationApi
@Composable
fun OverviewScreenContent(
    notesViewState: List<NoteViewState>,
    onNoteClick: (Int) -> Unit,
    withPassword: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = notesViewState.size) {
        listState.scrollToItem(0)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 92.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notesViewState) {
            NoteCard(
                note = it,
                modifier = Modifier.clickable {
                    if (it.password.isEmpty()) {
                        onNoteClick(it.id)
                    } else {
                        withPassword(it.id)
                    }
                }
            )
        }
    }
}