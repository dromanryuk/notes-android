package ru.dromanryuk.notes.feature_note.presentation.note.model

sealed class ModalBottomSheetAction {
    object DeleteNote : ModalBottomSheetAction()
    object ShareNote : ModalBottomSheetAction()
    object AddPassword : ModalBottomSheetAction()
    object CopyNote : ModalBottomSheetAction()
}
