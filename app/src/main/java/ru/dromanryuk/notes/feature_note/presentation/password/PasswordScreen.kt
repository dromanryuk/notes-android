package ru.dromanryuk.notes.feature_note.presentation.password

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.password.components.PasswordScreenContent
import ru.dromanryuk.notes.feature_note.presentation.password.model.PasswordInputEvent

@Composable
fun PasswordScreen(
    navigateBack: () -> Unit,
    navigateToNoteScreen: () -> Unit,
) {
    val viewModel: PasswordViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    DefaultScaffold(
        modifier = Modifier.background(MaterialTheme.colors.background),
        topBar = {},
        bottomBar = {},
        content = { PasswordScreenContent(
            state = state,
            onPasswordChanged = { sendEvent(PasswordInputEvent.OnPasswordChanged(it)) },
            onCleanPressed = { sendEvent(PasswordInputEvent.OnCleanPressed) },
            onFingerprintLogin = { sendEvent(PasswordInputEvent.OnFingerprintLogin) },
            onBackPressed = { navigateBack() }
        ) },
        floatingActionButton = {}
    )
    BackHandler(onBack = navigateBack)
    LaunchedEffect(key1 = state.isExitFromScreen) {
        if (state.isExitFromScreen)
            navigateBack()
    }
    LaunchedEffect(key1 = state.isLoginToNote) {
        if (state.isLoginToNote)
            navigateToNoteScreen()
    }
}

@Composable
@Preview
fun PreviewPasswordScreen() {
    PasswordScreen({}, {})
}