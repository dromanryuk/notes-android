package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dromanryuk.notes.feature_note.data.model.NoteTextModel
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class CreateNoteTextContentUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Int) = withContext(Dispatchers.Default) {
        noteRepository.addText(
            NoteTextModel(
                noteId = id,
                text = "",
            )
        )
    }
}