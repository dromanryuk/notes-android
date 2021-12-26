package ru.dromanryuk.notes.feature_note.presentation.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.note.components.BottomSheetContent
import ru.dromanryuk.notes.feature_note.presentation.note.components.NoteBottomAppBar
import ru.dromanryuk.notes.feature_note.presentation.note.components.NoteScreenContent
import ru.dromanryuk.notes.feature_note.presentation.note.components.NoteTopAppBar
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteEditingEvent
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalMaterialApi
@Composable
fun NoteScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: NoteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent { sendEvent(NoteEditingEvent.AdditionalActions(it)) }
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        DefaultScaffold(
            modifier = Modifier.background(MaterialTheme.colors.background),
            topBar = { NoteTopAppBar { sendEvent(NoteEditingEvent.ExitScreen) } },
            bottomBar = { NoteBottomAppBar(modalBottomSheetState, scope) },
            content = {
                NoteScreenContent(
                    state = state,
                    onTitleChange = { sendEvent(NoteEditingEvent.OnTitleChanged(it)) },
                    onContentChange = { sendEvent(NoteEditingEvent.OnContentChanged(it)) }
                )
            }
        ) {

        }
        BackHandler(onBack = navigateBack)
        LaunchedEffect(key1 = state.isExitFromScreen) {
            if (state.isExitFromScreen)
                navigateBack()
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        NoteScreen {}
    }
}