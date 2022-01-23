package ru.dromanryuk.notes.feature_note.presentation.password.createPassword

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.PasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.password.createPassword.model.CreatePasswordEvent

@InternalCoroutinesApi
@Composable
fun CreatePasswordScreen(
    navigateBack: () -> Unit,
    navigateFurther: (String) -> Unit,
) {
    val viewModel: CreatePasswordViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    PasswordScreen(
        navigateBack = navigateBack,
        navigateFurther = navigateFurther,
        onEditingFinished = { sendEvent(CreatePasswordEvent.OnEditingFinished(it)) },
        isNavigateFurther = state.isNavigateFurther,
        passwordInputType = state.passwordInputType,
    )
}