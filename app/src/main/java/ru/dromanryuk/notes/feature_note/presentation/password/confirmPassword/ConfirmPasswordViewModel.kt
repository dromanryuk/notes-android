package ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.domain.model.Password
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNotePasswordUseCase
import ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.model.ConfirmPasswordEvent
import ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.model.ConfirmPasswordState
import javax.inject.Inject

@HiltViewModel
class ConfirmPasswordViewModel @Inject constructor(
    private val useCases: ConfirmPasswordUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val noteId = savedStateHandle.get<Int>("noteId")
        ?: error("noteId argument is not passed")

    private val _state = MutableStateFlow(ConfirmPasswordState())
    val state = _state.asStateFlow()

    fun sendEvent(event: ConfirmPasswordEvent) {
        when (event) {
            is ConfirmPasswordEvent.OnEditingFinished -> onSaveEditing(event.password)
            is ConfirmPasswordEvent.SetPasswordValue -> setPasswordStateValue(event.password)
        }
    }

    private fun setPasswordStateValue(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun onSaveEditing(password: String) {
        viewModelScope.launch {
            if (_state.value.password == password) {
                _state.update {
                    it.copy(isNavigateFurther = true)
                }
                useCases.updateNotePasswordUseCase(
                    UpdateNotePasswordUseCase.Params(
                        id = noteId,
                        password = Password.CodePassword(password)
                    )
                )
            } else {
                _state.update {
                    it.copy(isNavigateBack = true)
                }
            }
        }
    }
}