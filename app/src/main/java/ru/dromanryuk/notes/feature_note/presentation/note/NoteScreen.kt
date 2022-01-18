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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.model.toText
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultScaffold
import ru.dromanryuk.notes.feature_note.presentation.note.components.*
import ru.dromanryuk.notes.feature_note.presentation.note.model.*
import ru.dromanryuk.notes.ui.theme.NotesTheme

@ExperimentalMaterialApi
@Composable
fun NoteScreen(
    navigateToOverviewScreen: () -> Unit,
    navigateToPasswordScreen: () -> Unit
) {
    val viewModel: NoteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val sendEvent = viewModel::sendEvent

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
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
            topBar = {
                NoteTopAppBar(
                    favouriteState = state.favouriteState,
                    onNavigateBack = { sendEvent(NoteEditingEvent.ExitScreen) },
                    onToggleFavourite = { sendEvent(NoteEditingEvent.ToggleFavourite) }
                )
            },
            bottomBar = { NoteBottomAppBar(modalBottomSheetState, scope) },
            content = {
                NoteScreenContent(
                    state = state,
                    onTitleChange = { sendEvent(NoteEditingEvent.OnTitleChanged(it)) },
                    onContentChange = { sendEvent(NoteEditingEvent.OnContentChanged(it)) }
                )
                ShareDialogWrapper(state, sendEvent, modalBottomSheetState, scope, viewModel)
                RemovePasswordDialogWrapper(state, sendEvent)
            }
        ) {

        }
        BackHandler(onBack = navigateToOverviewScreen)
        LaunchedEffect(key1 = state.isExitFromScreen) {
            if (state.isExitFromScreen)
                navigateToOverviewScreen()
        }
        LaunchedEffect(key1 = state.isNavigateToPasswordScreen) {
            if (state.isNavigateToPasswordScreen)
                navigateToPasswordScreen()
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
    viewModel: NoteViewModel,
) {
    if (state.shareDialogVisibility == UiComponentVisibility.Show) {
        ShareDialog(
            content = when (state.contentState) {
                is NoteContentState.Text -> state.contentState.toNoteTextContent().text
                is NoteContentState.Checklist -> state.contentState.toNoteChecklistContent()
                    .toText()
            },
            onDismiss = {
                sendEvent(NoteEditingEvent.UpdateShareDialogVisibility(UiComponentVisibility.Hide))
            },
            hideBottomSheet = {
                scope.launch {
                    sheetState.hide()
                }
            },
            uri = viewModel.getShareFile()
        )
    }
}

@Composable
private fun RemovePasswordDialogWrapper(
    state: NoteState,
    sendEvent: (NoteEditingEvent) -> Unit,
) {
    if (state.removePasswordDialogVisibility == UiComponentVisibility.Show)
        RemovePasswordDialog(
            onDismiss = { sendEvent(UpdateRemovePasswordDialogVisibility(UiComponentVisibility.Hide)) },
            onRemoveNotePassword = { sendEvent(RemoveNotePassword) }
        )
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        NoteScreen({}, {})
    }
}