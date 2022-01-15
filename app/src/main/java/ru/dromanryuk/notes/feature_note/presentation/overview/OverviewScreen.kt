package ru.dromanryuk.notes.feature_note.presentation.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.overview.components.*
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent.SortingTypeChanged
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent.UpdateSortDialogVisibility
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewState
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalFoundationApi
@Composable
fun OverviewScreen(onNoteClick: (Int) -> Unit) {
    val viewModel: OverviewViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    DefaultScaffold(
        modifier = Modifier,
        topBar = { OverviewTopAppBar {
            sendEvent(UpdateSortDialogVisibility(UiComponentVisibility.Show))
        } },
        bottomBar = { OverviewBottomAppBar(
            onAddChecklistNoteClick = { sendEvent(OverviewEvent.CreateChecklistNote) }
        ) },
        content = {
            OverviewScreenContent(state.noteViewStates, onNoteClick)
            SortingDialogWrapper(state, sendEvent)
        },
        floatingActionButton = {
            OverviewFloatingActionButton(
                onAddClick = { sendEvent(OverviewEvent.CreateTextNote) },
                navigateToNote = onNoteClick
            )
        }
    )
}

@Composable
private fun SortingDialogWrapper(
    state: OverviewState,
    sendEvent: (OverviewEvent) -> Unit,
) {
    if (state.sortingDialogVisibility == UiComponentVisibility.Show)
        SortingDialog(
            sortingType = state.filterState.sortingType,
            onDismiss = {
                sendEvent(UpdateSortDialogVisibility(UiComponentVisibility.Hide))
            },
            onChangeSortingType = { sendEvent(SortingTypeChanged(it)) }
        )
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun OverviewScreenPreview() {
    NotesTheme {
        OverviewScreen { }
    }
}