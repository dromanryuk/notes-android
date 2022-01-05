package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class ObserveNoteUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(id: Int): Flow<Note?> = noteRepository.observeById(id)
}