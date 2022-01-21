package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.password.biometric.BiometricLoginDialog
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordButtonType
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordState

@Composable
fun PasswordInputButtons(
    state: PasswordState,
    onPasswordChanged: (String) -> Unit,
    onCleanPressed: () -> Unit,
    onFingerprintLogin: () -> Unit,
    onBackPressed: () -> Unit,
    onFingerprintClick: (UiComponentVisibility) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        var showBiometricLoginDialog by remember { mutableStateOf(false) }
        if (state.fingerprintDialogVisibility == UiComponentVisibility.Show) {
            showBiometricLoginDialog = true
        } else if (state.fingerprintDialogVisibility == UiComponentVisibility.Hide) {
            showBiometricLoginDialog = false
        }
        Column() {
            state.buttons.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    it.forEach {
                        when (it) {
                            is PasswordButtonType.NumberButton -> {
                                PasswordNumberButton(
                                    digit = it.digit.toString(),
                                    modifier = Modifier.size(70.dp)
                                ) {
                                    onPasswordChanged(it.digit.toString())
                                }
                            }
                            is PasswordButtonType.TextButton -> {
                                PasswordTextButton(
                                    text = it.text,
                                    modifier = Modifier.size(70.dp)
                                ) {
                                    onBackPressed()
                                }
                            }
                            is PasswordButtonType.Fingerprint -> {
                                PasswordIconButton(
                                    icon = Icons.Filled.Fingerprint,
                                    modifier = Modifier.size(70.dp)
                                ) {
                                    onFingerprintClick(UiComponentVisibility.Show)
                                }
                            }
                            is PasswordButtonType.Clear -> {
                                PasswordIconButton(
                                    icon = Icons.Outlined.Backspace,
                                    modifier = Modifier.size(70.dp)
                                ) {
                                    onCleanPressed()
                                }
                            }
                        }
                    }
                }
            }
        }
        if (showBiometricLoginDialog)
            BiometricLoginDialog(
                onSuccess = { onFingerprintLogin() },
                onDismiss = {
                    onFingerprintClick(UiComponentVisibility.Hide)
                }
            )
    }
}

@Composable
@Preview
fun PreviewPasswordInputButtons() {
    PasswordInputButtons(
        PasswordState(), {}, {}, {}, {}, {}
    )
}
