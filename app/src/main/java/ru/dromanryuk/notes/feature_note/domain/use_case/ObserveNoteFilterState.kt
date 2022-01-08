package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteFilterRepository

class ObserveNoteFilterState(
    private val noteFilterRepository: NoteFilterRepository
) {
    operator fun invoke() = noteFilterRepository.observeNoteFilterState()
}