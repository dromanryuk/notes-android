package ru.dromanryuk.notes.feature_note.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilterState
import ru.dromanryuk.notes.feature_note.domain.filter.NoteSortingType
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewState
import ru.dromanryuk.notes.feature_note.presentation.overview.model.toNoteViewStates
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val useCases: OverviewUseCases,
) : ViewModel() {
    private val _state = MutableStateFlow(OverviewState())
    val state = _state.asStateFlow()

    init {
        observeNotes()
        observeNoteFilter()
    }

    private fun observeNotes() {
        useCases.observeNotes()
            .onEach { updateNotes(it) }
            .launchIn(viewModelScope)
    }

    private suspend fun updateNotes(list: List<Note?>) {
        val noteViewStates = list.toNoteViewStates()
        _state.update {
            it.copy(noteViewStates = noteViewStates)
        }
    }

    private fun observeNoteFilter() {
        useCases.observeNoteFilterState()
            .onEach { updateFilterState(it) }
            .launchIn(viewModelScope)
    }

    private fun updateFilterState(noteFilterState: NoteFilterState) {
        _state.update { it.copy(filterState = noteFilterState) }
    }

    fun sendEvent(event: OverviewEvent) {
        when (event) {
            OverviewEvent.CreateTextNote -> createTextNote()
            OverviewEvent.CreateChecklistNote -> createChecklistNote()
            is OverviewEvent.FavouriteFilterChanged -> { }
            is OverviewEvent.SearchTextChanged -> { }
            is OverviewEvent.SortingTypeChanged -> updateSortingType(event.sortingType)
            is OverviewEvent.UpdateSortDialogVisibility -> _state.update {
                it.copy(sortingDialogVisibility = event.visibility)
            }
        }
    }

    private fun createTextNote() = viewModelScope.launch {
        useCases.createNoteUseCase() {
            useCases.createNoteTextContentUseCase(it)
        }
    }

    private fun createChecklistNote() = viewModelScope.launch {
        useCases.createNoteUseCase() {
            useCases.createNoteChecklistUseCase(it)
        }
    }

    private fun updateSortingType(sortingType: NoteSortingType) = viewModelScope.launch {
        useCases.updateNoteFilterState(
            _state.value.filterState.copy(sortingType = sortingType)
        )
    }
}