package ru.dromanryuk.notes.feature_note.presentation.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.overview.components.OverviewBottomAppBar
import ru.dromanryuk.notes.feature_note.presentation.overview.components.OverviewFloatingActionButton
import ru.dromanryuk.notes.feature_note.presentation.overview.components.OverviewScreenContent
import ru.dromanryuk.notes.feature_note.presentation.overview.components.OverviewTopAppBar
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalFoundationApi
@Composable
fun OverviewScreen(onNoteClick: (Int) -> Unit) {
    val viewModel: OverviewViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    DefaultScaffold(
        modifier = Modifier,
        topBar = { OverviewTopAppBar() },
        bottomBar = { OverviewBottomAppBar() },
        content = { OverviewScreenContent(state.noteViewStates, onNoteClick) },
        floatingActionButton = { OverviewFloatingActionButton(
            onAddClick = { sendEvent(OverviewEvent.CreateNote) },
            navigateToNote = onNoteClick
        )
        }
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun OverviewScreenPreview() {
    NotesTheme {
        OverviewScreen {  }
    }
}