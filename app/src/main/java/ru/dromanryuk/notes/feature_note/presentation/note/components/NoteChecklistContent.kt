package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox

@Composable
fun NoteChecklistContent(
    checklist: List<Checkbox>?,
    onContentChange: (String) -> Unit
) {
    Text(text = "CHECKLIST")
}

@Composable
fun ChecklistItem(
    checkbox: Checkbox
) {
    val deleteButton = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checkbox.selected,
            onCheckedChange = {}
        )
        DefaultTextField(
            modifier = Modifier,
            value = "",
            onValueChange = {},
            fontSize = 16.sp,
            placeholder = "",
            maxLines = Int.MAX_VALUE)
    }
}

