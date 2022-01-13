package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class UpdateCheckboxUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(params: Params) {
        val oldCheckbox = noteRepository.getCheckboxById(params.id)
        val newCheckbox = oldCheckbox.createUpdated(params)
        noteRepository.updateCheckbox(params.noteId, newCheckbox)
        val note = noteRepository.getNoteById(params.noteId)
        noteRepository.updateNote(note)
    }

    private fun Checkbox.createUpdated(params: Params) = copy(
        id = params.id,
        content = params.content,
        selected = params.selected,
    )

    data class Params(
        val id: Int,
        val noteId: Int,
        val content: String,
        val selected: Boolean,
    )
}