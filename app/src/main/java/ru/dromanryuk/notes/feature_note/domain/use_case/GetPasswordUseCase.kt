package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class GetPasswordUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) = noteRepository.getPasswordById(noteId)
}