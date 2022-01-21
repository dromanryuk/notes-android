package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.*
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType.LOGIN
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType.REGISTRATION

class PasswordViewModel : ViewModel() {

    private val _state = MutableStateFlow(PasswordState())
    val state = _state.asStateFlow()

    init {
        updatePasswordButtons()
    }

    fun sendEvent(event: PasswordInputEvent) {
        when (event) {
            is PasswordInputEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is PasswordInputEvent.SetScreenType -> setScreenType(event.type)
            is PasswordInputEvent.UpdateFingerprintDialogVisibility -> onFingerprintClick(event.visibility)
            PasswordInputEvent.OnCleanPressed -> cleanPassword()
            PasswordInputEvent.OnFingerprintLogin -> onLoginToNote()
            PasswordInputEvent.ExitScreen -> onExitScreen()
            PasswordInputEvent.ClearAll -> clearAll()
        }
    }

    private fun clearAll() {
        _state.update {
            it.copy(password = flowOf(""))
        }
    }

    private fun setScreenType(type: PasswordInputType) {
        _state.update {
            if (it.passwordInputType != type) {
                onDifferentScreenTypes(type)
            }
            it.copy(passwordInputType = type)
        }
        updatePasswordButtons()
    }

    private fun onDifferentScreenTypes(type: PasswordInputType) {
        if (type == LOGIN) {
            _state.update { it.copy(fingerprintDialogVisibility = UiComponentVisibility.Show) }
        } else if (type == REGISTRATION) {
            _state.update { it.copy(fingerprintDialogVisibility = UiComponentVisibility.Hide) }
        }
    }

    private fun onLoginToNote() {
        _state.update { it.copy(isEditingFinished = true) }
    }

    private fun onFingerprintClick(visibility: UiComponentVisibility) {
        _state.update {
            it.copy(fingerprintDialogVisibility = visibility)
        }
    }

    private fun onPasswordChanged(password: String) = viewModelScope.launch {
        updatePasswordText(password)
        updatePasswordButtons()
    }

    private fun updatePasswordText(password: String) = viewModelScope.launch {
        _state.value.password.collect {
            _state.update { state ->
                if (it.length < 4) {
                    state.copy(password = flowOf(it + password))
                } else {
                    state.copy(password = flowOf(it))
                }
            }
        }
    }

    private fun onExitScreen() {
        _state.update { it.copy(isExitFromScreen = true) }
    }

    private fun cleanPassword() = viewModelScope.launch {
        _state.value.password.collect { password ->
            _state.update { it.copy(password = flowOf(password.dropLast(1))) }
        }
    }

    private fun updatePasswordButtons() = viewModelScope.launch {
        _state.value.password.collect { password ->
            _state.update {
                if (it.passwordInputType == LOGIN) {
                    if (password.isNotEmpty()) {
                        it.copy(buttons = generateButtonsMatrix(PasswordButtonType.Clear))
                    } else {
                        it.copy(buttons = generateButtonsMatrix(PasswordButtonType.Fingerprint))
                    }
                } else {
                    it.copy(buttons = generateButtonsMatrix(PasswordButtonType.Clear))
                }
            }
        }
    }
}