package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilterState
import ru.dromanryuk.notes.feature_note.domain.repository.NoteFilterRepository

class UpdateNoteFilterStateUseCase(
    private val noteFilterRepository: NoteFilterRepository
) {
    suspend operator fun invoke(filterState: NoteFilterState) =
        noteFilterRepository.updateNoteFilterState(filterState)
}