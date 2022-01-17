package ru.dromanryuk.notes.feature_note.presentation.password.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.dromanryuk.notes.feature_note.presentation.password.model.PasswordState

@Composable
fun PasswordScreenContent(
    state: PasswordState,
    onPasswordChanged: (String) -> Unit,
    onCleanPressed: () -> Unit,
    onFingerprintLogin: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (state.password.length != 4) {
            PasswordDisplayCircles(state.password, Modifier.weight(1f))
        } else if (state.password.length == 4) {
            PasswordDisplayCircles(state.verifiablePassword, Modifier.weight(1f))
        }
        PasswordInputButtons(
            state.buttons, onPasswordChanged, onCleanPressed, onFingerprintLogin, onBackPressed)
    }
}

@Composable
@Preview
fun PreviewPasswordScreenContent() {
    PasswordScreenContent(PasswordState(), {}, {}, {}, {})
}