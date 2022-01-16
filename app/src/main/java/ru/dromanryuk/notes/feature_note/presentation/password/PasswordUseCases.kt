package ru.dromanryuk.notes.feature_note.presentation.password

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.CheckPasswordPresence
import ru.dromanryuk.notes.feature_note.domain.use_case.GetPasswordUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNotePasswordUseCase
import javax.inject.Inject

class PasswordUseCases @Inject constructor(
    noteRepository: NoteRepository,
) {
    val getPasswordUseCase = GetPasswordUseCase(noteRepository)
    val updateNotePasswordUseCase = UpdateNotePasswordUseCase(noteRepository)
    val checkPasswordPresence = CheckPasswordPresence(noteRepository)
}