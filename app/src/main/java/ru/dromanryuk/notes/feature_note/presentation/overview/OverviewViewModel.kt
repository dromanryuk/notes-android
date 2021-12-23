package ru.dromanryuk.notes.feature_note.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewEvent
import ru.dromanryuk.notes.feature_note.presentation.overview.model.OverviewState
import ru.dromanryuk.notes.feature_note.presentation.overview.model.toNoteViewStates
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val overviewUseCases: OverviewUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(OverviewState())
    val state = _state.asStateFlow()

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            overviewUseCases.observeNotes().collect { noteModelList ->
                _state.update {
                    it.copy(noteViewStates = noteModelList.toNoteViewStates())
                }
            }
        }
    }

    fun sendEvent(event: OverviewEvent) {
        when(event) {
            OverviewEvent.CreateNote -> createTextNote()
            is OverviewEvent.FavouriteFilterChanged -> {

            }
            is OverviewEvent.SearchTextChanged -> {

            }
            is OverviewEvent.SortingTypeChanged -> TODO()
            is OverviewEvent.UpdateSortDialogVisibility -> TODO()
        }
    }

    private fun createTextNote() = viewModelScope.launch {
        overviewUseCases.createNoteUseCase() {
            overviewUseCases.createNoteTextContentUseCase.invoke(it)
        }
    }

    private fun createChecklistNote() = viewModelScope.launch {

    }
}