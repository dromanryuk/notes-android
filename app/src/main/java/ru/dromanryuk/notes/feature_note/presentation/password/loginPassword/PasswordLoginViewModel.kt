package ru.dromanryuk.notes.feature_note.presentation.password.loginPassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.domain.use_case.VerificatePasswordUseCase
import ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.model.PasswordLoginEvent
import ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.model.PasswordLoginState
import javax.inject.Inject

@HiltViewModel
class PasswordLoginViewModel @Inject constructor(
    private val useCases: PasswordLoginUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val noteId = savedStateHandle.get<Int>("noteId")
        ?: error("noteId argument is not passed")

    private val _state = MutableStateFlow(PasswordLoginState())
    val state = _state.asStateFlow()

    fun sendEvent(event: PasswordLoginEvent) {
        when (event) {
            is PasswordLoginEvent.OnEditingFinished -> verificatePassword(event.password)
            PasswordLoginEvent.ResetCleanState -> cleanCleanState()
        }
    }

    private fun cleanCleanState() {
        _state.update {
            it.copy(resetInputValue = false)
        }
    }

    private fun verificatePassword(password: String) = viewModelScope.launch {
        val result = useCases.verificatePasswordUseCase(VerificatePasswordUseCase.Params(noteId, password))
        _state.update {
            if (result) {
                it.copy(isNavigateFurther = result, resetInputValue = false)
            } else {
                it.copy(isNavigateFurther = result, resetInputValue = true)
            }
        }
    }
}