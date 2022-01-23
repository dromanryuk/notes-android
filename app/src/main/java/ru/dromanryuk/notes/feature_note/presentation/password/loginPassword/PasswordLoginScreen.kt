package ru.dromanryuk.notes.feature_note.presentation.password.loginPassword

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.PasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.model.PasswordLoginEvent

@Composable
fun PasswordLoginScreen(
    navigateBack: () -> Unit,
    navigateFurther: (String) -> Unit,
) {
    val viewModel: PasswordLoginViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    PasswordScreen(
        navigateBack = navigateBack,
        navigateFurther = navigateFurther,
        onEditingFinished = { sendEvent(PasswordLoginEvent.OnEditingFinished(it)) },
        isNavigateFurther = state.isNavigateFurther,
        passwordInputType = state.passwordInputType,
        isCleanInputPassword = state.resetInputValue,
        resetCleanState = { sendEvent(PasswordLoginEvent.ResetCleanState) }
    )
}