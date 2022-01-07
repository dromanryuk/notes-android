package ru.dromanryuk.notes.feature_note.presentation.note

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import ru.dromanryuk.notes.feature_note.domain.model.toText
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNoteUseCase
import ru.dromanryuk.notes.feature_note.presentation.note.model.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val noteId = savedStateHandle.get<Int>("noteId")
        ?: error("noteId argument is not passed")

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    init {
        observeNote()
    }

    private fun observeNote() {
        noteUseCases.observeNoteUseCase(noteId)
            .onEach { note ->
                _state.update {
                    it.copy(
                        titleState = note!!.name,
                        contentState = when (note.content) {
                            is NoteContent.TextNote -> NoteContentState.Text(text = note.content.text)
                            is NoteContent.ChecklistNote -> NoteContentState.Checklist(checklist = note.content.checkboxes)
                        },
                        favouriteState = note.isFavourite,
                        editingDateTime = note.metadata.editingDateTime.toString(),
                        isExitFromScreen = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun sendEvent(event: NoteEditingEvent) {
        when (event) {
            is NoteEditingEvent.OnTitleChanged -> changeTitle(event.title)
            is NoteEditingEvent.OnContentChanged -> when (_state.value.contentState) {
                is NoteContentState.Text -> setTextContent(event.content)
                is NoteContentState.Checklist -> {
                }
            }
            is NoteEditingEvent.AdditionalActions ->
                when(event.action) {
                ModalBottomSheetAction.AddPassword -> {}
                ModalBottomSheetAction.CopyNote -> {}
                ModalBottomSheetAction.DeleteNote -> removeNote()
                ModalBottomSheetAction.ShareNote -> {
                    sendUpdateShareDialogVisibility()
                }
            }
            is NoteEditingEvent.UpdateShareDialogVisibility -> {
                _state.update { it.copy(shareDialogVisibility = event.visibility) }
            }
            NoteEditingEvent.ExitScreen -> {
                if (checkNoteFilling()) {
                    removeNote()
                } else {
                    sendSaveEditingEvent()
                    onExitScreen()
                }
            }
            NoteEditingEvent.SaveEditing -> onSaveEditing(_state.value)
            NoteEditingEvent.ToggleFavourite -> changeFavourite()
            NoteEditingEvent.SetReminder -> {
            }
        }
    }

    private fun changeFavourite() = viewModelScope.launch {
        noteUseCases.toggleFavourite(noteId)
    }

    fun getShareFile(): Uri {
        val content = when (_state.value.contentState) {
            is NoteContentState.Text -> _state.value.contentState.toNoteTextContent().text
            is NoteContentState.Checklist -> _state.value.contentState.toNoteChecklistContent()
                .toText()
        }
        val title = _state.value.titleState
        return noteUseCases.shareNoteUseCase(content, title)
    }

    private fun sendUpdateShareDialogVisibility() {
        sendEvent(NoteEditingEvent.UpdateShareDialogVisibility(UiComponentVisibility.Show))
    }

    private fun checkNoteFilling(): Boolean {
        return (_state.value.titleState.isEmpty()
                && _state.value.contentState.text.isNullOrEmpty()
                && _state.value.contentState.checklist.isNullOrEmpty())
    }

    private fun removeNote() = viewModelScope.launch {
        onExitScreen()
        noteUseCases.removeNoteUseCase(noteId)
    }

    private fun changeTitle(title: String) = viewModelScope.launch {
        _state.update {
            it.copy(titleState = title)
        }
    }

    private fun changeContent(text: String) = viewModelScope.launch {
        when (_state.value.contentState) {
            is NoteContentState.Text -> setTextContent(text)//_state.value.contentState.text = text
            is NoteContentState.Checklist -> _state.value.contentState.checklist
        }
    }

    private fun setTextContent(text: String) {
        _state.update {
            it.copy(contentState = NoteContentState.Text(text = text))
        }
    }

    private fun onSaveEditing(value: NoteState) = with(value) {
        viewModelScope.launch {
            noteUseCases.updateNoteUseCase(
                UpdateNoteUseCase.Params(
                    id = noteId,
                    title = titleState,
                    content = when (contentState) {
                        is NoteContentState.Text -> _state.value.contentState.toNoteTextContent()
                        is NoteContentState.Checklist -> _state.value.contentState.toNoteChecklistContent()
                    },
                    isFavourite = favouriteState
                )
            )
        }
    }

    private fun onExitScreen() {
        _state.update { it.copy(isExitFromScreen = true) }
    }

    private fun sendSaveEditingEvent() {
        sendEvent(NoteEditingEvent.SaveEditing)
    }
}