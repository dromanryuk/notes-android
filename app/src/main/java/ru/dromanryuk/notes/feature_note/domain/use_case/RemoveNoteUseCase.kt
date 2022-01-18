package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class RemoveNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) {
        noteRepository.removeNote(noteId)
    }
}