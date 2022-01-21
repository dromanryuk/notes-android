package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.components.PasswordScreenContent
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputEvent
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType

@Composable
fun PasswordScreen(
    navigateBack: () -> Unit,
    navigateFurther: (String) -> Unit,
    onEditingFinished: (String) -> Unit,
    isNavigateFurther: Boolean,
    passwordInputType: PasswordInputType,
    isCleanInputPassword: Boolean = false,
    resetCleanState: () -> Unit = {}
) {
    val viewModel: PasswordViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    sendEvent(PasswordInputEvent.SetScreenType(passwordInputType))

    DefaultScaffold(
        modifier = Modifier.background(MaterialTheme.colors.background),
        topBar = {},
        bottomBar = {},
        content = {
            PasswordScreenContent(
                state = state,
                onPasswordChanged = { sendEvent(PasswordInputEvent.OnPasswordChanged(it)) },
                onCleanPressed = { sendEvent(PasswordInputEvent.OnCleanPressed) },
                onFingerprintLogin = { sendEvent(PasswordInputEvent.OnFingerprintLogin) },
                onBackPressed = { navigateBack() },
                onFingerprintClick = {
                    sendEvent(PasswordInputEvent.UpdateFingerprintDialogVisibility(it))
                }
            )
        },
        floatingActionButton = {},
    )
    BackHandler(onBack = navigateBack)
    LaunchedEffect(key1 = state.isExitFromScreen) {
        if (state.isExitFromScreen)
            navigateBack()
    }
    LaunchedEffect(key1 = state.isEditingFinished, key2 = isNavigateFurther) {
        if (state.isEditingFinished || isNavigateFurther) {
            state.password.collect {
                navigateFurther(it)
            }
        }
    }
    LaunchedEffect(key1 = state.password) {
        state.password.collect {
            if (it.length == 4) {
                onEditingFinished(it)
            }
        }
    }
    SideEffect {
        if (isCleanInputPassword) {
            sendEvent(PasswordInputEvent.ClearAll)
            resetCleanState()
        }
    }
}

@Composable
@Preview
fun PreviewPasswordScreen() {
    PasswordScreen({}, {}, {}, false, PasswordInputType.LOGIN, false, {})
}