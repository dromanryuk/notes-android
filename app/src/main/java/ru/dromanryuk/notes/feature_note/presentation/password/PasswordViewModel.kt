package ru.dromanryuk.notes.feature_note.presentation.password

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
import ru.dromanryuk.notes.feature_note.presentation.password.model.*
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val useCases: PasswordUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val noteId = savedStateHandle.get<Int>("noteId")
        ?: error("noteId argument is not passed")

    private val _state = MutableStateFlow(PasswordState())
    val state = _state.asStateFlow()

    init {
        checkPassword()
    }

    private fun checkPassword() = viewModelScope.launch {
        val passwordPresence = useCases.checkPasswordPresence(noteId)
        if (passwordPresence) {
            getPassword()
        }
    }

    private fun getPassword() = viewModelScope.launch {
        _state.update {
            it.copy(
                verifiablePassword = useCases.getPasswordUseCase(noteId)
            )
        }
    }

    fun sendEvent(event: PasswordInputEvent) {
        when (event) {
            is PasswordInputEvent.OnPasswordChanged -> {
                if (_state.value.password.length < 4) {
                    onPasswordChanged(event.password)
                } else {
                    onVerifiablePasswordChanged(event.password)
                }
                if (_state.value.verifiablePassword.length == 4) {
                    if (_state.value.password == _state.value.verifiablePassword) {
                        savePassword()
                        onExitScreen()
                    }
                }
            }
            PasswordInputEvent.ExitScreen -> onExitScreen()
            PasswordInputEvent.OnCleanPressed -> cleanPassword()
            PasswordInputEvent.OnFingerprintLogin -> TODO()
            PasswordInputEvent.SaveEditing -> onSaveEditing(_state.value)
        }
    }

    private fun savePassword() {
        sendEvent(PasswordInputEvent.SaveEditing)
    }

    private fun cleanPassword() = viewModelScope.launch {
        _state.update {
            it.copy(password = it.password.dropLast(1))
        }
        updatePasswordButtons()
    }

    private fun onSaveEditing(state: PasswordState) = with(state) {
        viewModelScope.launch {
            useCases.updateNotePasswordUseCase(
                UpdateNotePasswordUseCase.Params(
                    id = noteId,
                    password = Password.CodePassword(state.password)
                )
            )
        }
    }

    private fun onPasswordChanged(password: String) = viewModelScope.launch {
        updatePasswordText(password)
        updatePasswordButtons()
    }

    private fun onVerifiablePasswordChanged(password: String) = viewModelScope.launch {
        updateVerifiablePasswordText(password)
        updatePasswordButtons()
    }

    private fun updateVerifiablePasswordText(verifiablePassword: String) {
        _state.update {
            if (it.verifiablePassword.length < 4) {
                it.copy(verifiablePassword = it.verifiablePassword + verifiablePassword)
            } else {
                it.copy(verifiablePassword = it.verifiablePassword)
            }
        }
    }

    private fun updatePasswordText(password: String) {
        _state.update {
            if (it.password.length < 4) {
                it.copy(password = it.password + password)
            } else {
                it.copy(password = it.password)
            }
        }
    }

    private fun updatePasswordButtons() {
        _state.update {
            if (it.password.isNotEmpty()) {
                it.copy(buttons = generateButtonsMatrix(PasswordButtonType.Clear))
            } else {
                it.copy(buttons = generateButtonsMatrix(PasswordButtonType.Fingerprint))
            }
        }
    }

    private fun onExitScreen() {
        _state.update { it.copy(isExitFromScreen = true) }
    }
}