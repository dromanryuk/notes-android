package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteContentState
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteState
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun NoteScreenContent(
    state: NoteState,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .navigationBarsWithImePadding()
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.titleState,
            onValueChange = onTitleChange,
            fontSize = 20.sp,
            placeholder = "Название",
            maxLines = 1
        )
        when (state.contentState) {
            is NoteContentState.Text -> NoteTextContent(
                state.contentState.text,
                onContentChange
            )
            is NoteContentState.Checklist -> NoteChecklistContent(
                state.contentState.checklist,
                onContentChange
            )
        }
    }
}

@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        NoteScreenContent(
            onTitleChange = {  },
            onContentChange = {  },
            state = NoteState()
        )
    }
}