package ru.dromanryuk.notes.feature_note.presentation.note

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.domain.model.*
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateCheckboxUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNoteUseCase
import ru.dromanryuk.notes.feature_note.presentation.note.model.*
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteDialogs.*
import ru.dromanryuk.notes.feature_notification.model.changeDialogVisibility
import ru.dromanryuk.notes.feature_notification.model.changeMenuVisibility
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
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
        useCases.observeNoteUseCase(noteId)
            .onEach { note ->
                if (note != null) {
                    onNewNote(note)
                } else {
                    onNoNote()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onNewNote(note: Note) {
        _state.update {
            it.copy(
                titleState = note.name,
                contentState = when (note.content) {
                    is NoteContent.TextNote -> NoteContentState.Text(text = note.content.text)
                    is NoteContent.ChecklistNote -> NoteContentState.Checklist(
                        checklist = generateChecklistContent(note.content.checkboxes)
                    )
                },
                favouriteState = note.isFavourite,
                editingDateTime = note.metadata.editingDateTime.toString(),
                passwordState = note.password
            )
        }
    }

    private fun onNoNote() {
        _state.update {
            it.copy(
                isExitFromScreen = true
            )
        }
    }

    private fun generateChecklistContent(checkboxes: List<Checkbox>): ChecklistState {
        val selectedList = mutableListOf<Checkbox>()
        val unselectedList = mutableListOf<Checkbox>()
        checkboxes.forEach {
            if (it.selected) {
                selectedList.add(it)
            } else {
                unselectedList.add(it)
            }
        }
        return ChecklistState(selectedList, unselectedList)
    }

    fun sendEvent(event: NoteEditingEvent) {
        when (event) {
            is NoteEditingEvent.OnTitleChanged -> changeTitle(event.title)
            is NoteEditingEvent.OnTextContentChanged -> when (_state.value.contentState) {
                is NoteContentState.Text -> setTextContent(event.content)
                //is NoteContentState.Checklist -> setChecklistContent(event.content)
            }
            is NoteEditingEvent.OnCheckboxChanged ->
                updateCheckbox(event.checkbox.id, event.checkbox.content, event.checkbox.selected)
            is NoteEditingEvent.AdditionalActions ->
                when (event.action) {
                    ModalBottomSheetAction.AddPassword -> {
                        when (_state.value.passwordState) {
                            is Password.NonePassword -> onNavigateToPasswordScreen()
                            is Password.CodePassword -> sendUpdateRemoveNotePasswordDialogVisibility()
                        }
                    }
                    ModalBottomSheetAction.CopyNote -> {
                    }
                    ModalBottomSheetAction.DeleteNote -> removeNote()
                    ModalBottomSheetAction.ShareNote -> updateNoteDialogState(SHARE, true)
                }
            is NoteEditingEvent.UpdateDialogVisibility -> {
                updateNoteDialogState(event.dialog, event.visibility)
            }
            is NoteEditingEvent.UpdateNotificationDialogMenuVisibility -> {
                _state.update { it.copy(notificationDialogVisibility = it.notificationDialogVisibility.changeMenuVisibility(event.type, event.newVal))}
            }
            NoteEditingEvent.AddCheckbox -> addCheckbox()
            NoteEditingEvent.ExitScreen -> {
                if (checkNoteFilling(_state.value)) {
                    removeNote()
                } else {
                    sendSaveEditingEvent()
                    onExitScreen()
                }
            }
            NoteEditingEvent.SaveEditing -> onSaveEditing(_state.value)
            NoteEditingEvent.RemoveNotePassword -> removeNotePassword()
            NoteEditingEvent.ToggleFavourite -> changeFavourite()
            NoteEditingEvent.SetReminder -> {
            }
        }
    }

    private fun updateCheckbox(id: Int, content: String, selected: Boolean) = viewModelScope.launch{
        useCases.updateCheckboxUseCase(
            UpdateCheckboxUseCase.Params(id, noteId, content, selected)
        )
    }

    private fun addCheckbox() = viewModelScope.launch {
        useCases.createNoteChecklistUseCase(noteId)
    }

    private fun changeFavourite() = viewModelScope.launch {
        useCases.toggleFavourite(noteId)
    }

    fun getShareFile(): Uri {
        val content = when (_state.value.contentState) {
            is NoteContentState.Text -> _state.value.contentState.toNoteTextContent().text
            is NoteContentState.Checklist -> _state.value.contentState.toNoteChecklistContent()
                .toText()
        }
        val title = _state.value.titleState
        return useCases.shareNoteUseCase(content, title)
    }

    private fun checkNoteFilling(state: NoteState): Boolean {
        if (state.titleState.isEmpty()) {
            when(state.contentState) {
                is NoteContentState.Text -> if(state.contentState.text.isNullOrEmpty()) return true
                is NoteContentState.Checklist ->
                    if(state.contentState.checklist != null) {
                        if (state.contentState.checklist?.selectedList.isNullOrEmpty() &&
                                state.contentState.checklist?.unselectedList.isNullOrEmpty()) {
                            return true
                        }
                    }
            }
        }
        return false
    }

    private fun removeNote() = viewModelScope.launch {
        onExitScreen()
        useCases.removeNoteUseCase(noteId)
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

    private fun onSaveEditing(state: NoteState) = with(state) {
        viewModelScope.launch {
            useCases.updateNoteUseCase(
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

    private fun updateNoteDialogState(dialog: NoteDialogs, visibility: Boolean) {
        when(dialog) {
            SHARE -> _state.update { it.copy(shareDialogVisible = visibility) }
            REMOVE_PASSWORD -> _state.update { it.copy(removePasswordDialogVisible = visibility) }
            NOTIFICATION -> _state.update { it.copy(notificationDialogVisibility = it.notificationDialogVisibility.changeDialogVisibility(visibility))}
        }
    }

    private fun removeNotePassword() = viewModelScope.launch {
        useCases.removePasswordUseCase(noteId)
    }

    private fun onExitScreen() {
        _state.update { it.copy(isExitFromScreen = true) }
    }

    private fun onNavigateToPasswordScreen() {
        _state.update { it.copy(isNavigateToPasswordScreen = true) }
    }

    private fun sendSaveEditingEvent() {
        sendEvent(NoteEditingEvent.SaveEditing)
    }
}