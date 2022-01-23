package ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNotePasswordUseCase
import javax.inject.Inject

class ConfirmPasswordUseCases @Inject constructor(
    noteRepository: NoteRepository,
) {
    val updateNotePasswordUseCase = UpdateNotePasswordUseCase(noteRepository)
}