package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class ObserveNotesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() = noteRepository.observeAll()
}