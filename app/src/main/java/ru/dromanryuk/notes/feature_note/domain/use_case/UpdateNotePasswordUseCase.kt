package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.model.Password
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class UpdateNotePasswordUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(params: Params) {
        updatePassword(params)
    }

    private suspend fun updatePassword(params: Params) {
        val oldNote = noteRepository.getNoteById(params.id)
        val newNote = oldNote.copy(password = params.password)
        noteRepository.updateNote(newNote)
    }

    data class Params(
        val id: Int,
        val password: Password
    )
}