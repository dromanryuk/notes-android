package ru.dromanryuk.notes.feature_note.presentation.password.createPassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.feature_note.presentation.password.createPassword.model.CreatePasswordEvent
import ru.dromanryuk.notes.feature_note.presentation.password.createPassword.model.CreatePasswordState
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class CreatePasswordViewModel @Inject constructor(
    private val useCases: CreatePasswordUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(CreatePasswordState())
    val state = _state.asStateFlow()

    fun sendEvent(event: CreatePasswordEvent) {
        when (event) {
            is CreatePasswordEvent.OnEditingFinished -> navigateFurther()
        }
    }

     private fun navigateFurther() = viewModelScope.launch {
        _state.update {
            it.copy(isNavigateFurther = true)
        }
    }
}