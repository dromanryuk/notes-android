package ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.PasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.model.ConfirmPasswordEvent

@Composable
fun ConfirmPasswordScreen(
    navigateBack: () -> Unit,
    navigateFurther: (String) -> Unit,
    password: String?,
) {
    val viewModel: ConfirmPasswordViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    if (!password.isNullOrEmpty()) sendEvent(ConfirmPasswordEvent.SetPasswordValue(password))
    LaunchedEffect(key1 = state.isNavigateBack) {
        if (state.isNavigateBack) {
            navigateBack()
        }
    }

    PasswordScreen(
        navigateBack = navigateBack,
        navigateFurther = navigateFurther,
        onEditingFinished = { sendEvent(ConfirmPasswordEvent.OnEditingFinished(it)) },
        isNavigateFurther = state.isNavigateFurther,
        passwordInputType = state.passwordInputType,
    )
}