package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class CopyNoteUseCase(
    private val noteRepository: NoteRepository
) {

}