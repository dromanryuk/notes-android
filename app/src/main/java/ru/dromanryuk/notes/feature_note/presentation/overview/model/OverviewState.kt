package ru.dromanryuk.notes.feature_note.presentation.overview.model

import ru.dromanryuk.notes.core.ProgressBarState
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilterState

data class OverviewState(
    val noteViewStates: List<NoteViewState> = emptyList(),
    val progressBarState: ProgressBarState = ProgressBarState.Loading,
    val filterState: NoteFilterState = NoteFilterState(),
    val sortingDialogVisibility: UiComponentVisibility = UiComponentVisibility.Hide
)