package ru.dromanryuk.notes.feature_note.presentation.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.note.components.*
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteEditingEvent
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteState
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
            BottomSheetContent(
                bottomSheetEvent = { sendEvent(NoteEditingEvent.AdditionalActions(it)) },
            )
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
                ShareDialogWrapper(state, sendEvent, modalBottomSheetState, scope)
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
@Composable
private fun ShareDialogWrapper(
    state: NoteState,
    sendEvent: (NoteEditingEvent) -> Unit,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    val context = LocalContext.current
    if (state.shareDialogVisibility == UiComponentVisibility.Show) {
        ShareDialog(
            onDismiss = {
                sendEvent(NoteEditingEvent.UpdateShareDialogVisibility(UiComponentVisibility.Hide))
            },
            onChangeShareType = {
                sendEvent(NoteEditingEvent.ShareTypeChanged(it, context))
            },
            hideBottomSheet = {
                scope.launch {
                    sheetState.hide()
                }
            }
        )
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