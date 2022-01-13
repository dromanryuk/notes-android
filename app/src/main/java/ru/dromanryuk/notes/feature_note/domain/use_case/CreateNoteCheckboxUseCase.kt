package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dromanryuk.notes.feature_note.data.model.NoteCheckboxModel
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class CreateNoteCheckboxUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) = withContext(Dispatchers.Default) {
        noteRepository.addCheckbox(
            NoteCheckboxModel(
                noteId = noteId,
                content = "",
                selected = false
            )
        )
        val note = noteRepository.getNoteById(noteId)
        noteRepository.updateNote(note)
    }
}