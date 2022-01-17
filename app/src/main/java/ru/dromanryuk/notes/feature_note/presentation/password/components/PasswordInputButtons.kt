package ru.dromanryuk.notes.feature_note.presentation.password.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dromanryuk.notes.feature_note.presentation.password.model.PasswordButtonType
import ru.dromanryuk.notes.feature_note.presentation.password.model.generateButtonsMatrix

@Composable
fun PasswordInputButtons(
    buttons: List<List<PasswordButtonType>>,
    onPasswordChanged: (String) -> Unit,
    onCleanPressed: () -> Unit,
    onFingerprintLogin: () -> Unit,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column() {
            buttons.forEach {
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
                                    onFingerprintLogin()
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
    }
}

@Composable
@Preview
fun PreviewPasswordInputButtons() {
    PasswordInputButtons(
        generateButtonsMatrix(PasswordButtonType.Fingerprint),
        {}, {}, {}, {}
    )
}
