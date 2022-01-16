package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.model.Password
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class CheckPasswordPresence(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(noteId: Int): Boolean {
        val note = noteRepository.getNoteById(noteId)
        return when (note.password) {
            is Password.NonePassword -> false
            is Password.CodePassword -> true
        }
    }
}