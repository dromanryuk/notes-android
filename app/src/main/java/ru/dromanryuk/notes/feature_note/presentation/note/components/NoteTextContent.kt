package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NoteTextContent(
    state: String?,
    onContentChange: (String) -> Unit
) {
    DefaultTextField(
        modifier = Modifier.fillMaxSize(),
        value = state!!,
        onValueChange = onContentChange,
        fontSize = 16.sp,
        placeholder = "Текст",
        maxLines = Int.MAX_VALUE
    )
}