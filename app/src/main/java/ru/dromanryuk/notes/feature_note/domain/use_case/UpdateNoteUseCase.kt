package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.datetime.Clock
import ru.dromanryuk.notes.feature_note.domain.model.Metadata
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(params: Params) {
        //noteRepository.update(note)
        updateNote(params)
    }

    private suspend fun updateNote(params: Params) {
        val oldNote = noteRepository.getById(params.id)
        val newNote = oldNote!!.createUpdated(params)
        noteRepository.updateNote(newNote)
        when (params.content) {
            is NoteContent.TextNote -> noteRepository.updateNoteTextContent(params.id,
                params.content.text)
            is NoteContent.ChecklistNote -> noteRepository.updateNoteChecklistContent()
        }
    }

    private fun Note.createUpdated(params: Params) = copy(
        name = params.title,
        content = params.content,
        isFavourite = params.isFavourite,
        metadata = Metadata(editingDateTime = Clock.System.now())
    )

    data class Params(
        val id: Int,
        val title: String,
        val content: NoteContent,
        val isFavourite: Boolean,
    )
}