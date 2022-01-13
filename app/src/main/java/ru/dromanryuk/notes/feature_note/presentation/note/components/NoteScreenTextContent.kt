package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteContentState
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteState
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun NoteScreenTextContent(
    state: NoteState,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    //LaunchedEffect(Unit) { scrollState.animateScrollTo(10000) }
    LaunchedEffect(scrollState.value) {
//        scrollState.scrollTo(scrollState.maxValue)
        scrollState.animateScrollTo(scrollState.value)
    }
//    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 50.dp)
            //.systemBarsPadding()
//            .navigationBarsWithImePadding()
            //.statusBarsPadding()
            //.imePadding()
//            .focusRequester(focusRequester)
            .verticalScroll(scrollState)
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
        NoteTextContent(
            state.contentState.text,
            onContentChange
        )
//        when (state.contentState) {
//            is NoteContentState.Text -> NoteTextContent(
//                state.contentState.text,
//                onContentChange
//            )
//            is NoteContentState.Checklist -> NoteChecklistContent(
//                state.contentState.checklist!!.unselectedList,
//                state.contentState.checklist!!.selectedList,
//                onContentChange
//            )
//        }
    }
}

@Preview
@Composable
private fun NoteScreenTextContentPreview() {
    NotesTheme {
        NoteScreenTextContent(
            onTitleChange = { },
            onContentChange = { },
            state = NoteState()
        )
    }
}