package ru.dromanryuk.notes.feature_note.presentation.password.loginPassword

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.VerificatePasswordUseCase
import javax.inject.Inject

class PasswordLoginUseCases @Inject constructor(
    noteRepository: NoteRepository,
) {
    val verificatePasswordUseCase = VerificatePasswordUseCase(noteRepository)
}