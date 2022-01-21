package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordState

@Composable
fun PasswordScreenContent(
    state: PasswordState,
    onPasswordChanged: (String) -> Unit,
    onCleanPressed: () -> Unit,
    onFingerprintLogin: () -> Unit,
    onBackPressed: () -> Unit,
    onFingerprintClick: (UiComponentVisibility) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PasswordDisplayCircles(state.password.collectAsState(initial = ""), Modifier.weight(1f))
        PasswordInputButtons(
            state, onPasswordChanged, onCleanPressed, onFingerprintLogin, onBackPressed, onFingerprintClick)
    }
}

@Composable
@Preview
fun PreviewPasswordScreenContent() {
    PasswordScreenContent(PasswordState(), {}, {}, {}, {}, {})
}