package ru.dromanryuk.notes.feature_note.presentation.password.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity

@Composable
fun OnCanBiometricLogin(action: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (context.canUseBiometricLogin()) {
            action()
        }
    }
}

private fun Context.canUseBiometricLogin() = BiometricManager.from(this)
    .run { canAuthenticate(DEFAULT_AUTHENTICATOR) == BiometricManager.BIOMETRIC_SUCCESS}

fun FragmentActivity.loginByBiometrics(onSuccess: () -> Unit, onFail: () -> Unit) =
    BiometricPrompt(this, BiometricLoginCallback(onSuccess, onFail))
        .authenticate(createPromptInfo())

private fun Context.createPromptInfo() =
    BiometricPrompt.PromptInfo.Builder()
        .setTitle("Вход в заметку")
        .setNegativeButtonText("Использовать пароль")
        .setAllowedAuthenticators(DEFAULT_AUTHENTICATOR)
        .build()

private const val DEFAULT_AUTHENTICATOR = BiometricManager.Authenticators.BIOMETRIC_WEAK