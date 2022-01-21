package ru.dromanryuk.notes.feature_note.presentation.password.biometric

import androidx.biometric.BiometricPrompt

class BiometricLoginCallback(
    private val onSuccess: () -> Unit,
    private val onFail: () -> Unit
) : BiometricPrompt.AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) = onFail()

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) =
        onSuccess()

    override fun onAuthenticationFailed() = onFail()
}