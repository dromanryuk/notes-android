package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class ToggleFavouriteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) = withContext(Dispatchers.Default) {
        val oldNote = noteRepository.getNoteById(noteId)
        val newNote = oldNote.toggledFavourite()
        noteRepository.updateNote(newNote)
    }

    private fun Note.toggledFavourite() = copy(isFavourite = !isFavourite)
}