package ru.dromanryuk.notes.feature_note.presentation.password.biometric

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.launch

@Composable
fun BiometricLoginDialog(
    onSuccess: () -> Unit,
    onDismiss: () -> Unit
) {
    val activity = LocalContext.current as FragmentActivity
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            activity.loginByBiometrics(
                onSuccess,
                onDismiss)
        }
    }
}