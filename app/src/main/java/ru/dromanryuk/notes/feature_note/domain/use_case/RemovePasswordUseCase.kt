package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.data.model.toNoteModel
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class RemovePasswordUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) {
        val note = noteRepository.getNoteById(noteId).toNoteModel()
        noteRepository.removePassword(note.copy(password = null))
    }
}