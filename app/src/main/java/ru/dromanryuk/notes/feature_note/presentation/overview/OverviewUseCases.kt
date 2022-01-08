package ru.dromanryuk.notes.feature_note.presentation.overview

import ru.dromanryuk.notes.feature_note.domain.repository.NoteFilterRepository
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.*
import javax.inject.Inject

class OverviewUseCases @Inject constructor(
    noteRepository: NoteRepository,
    noteFilterRepository: NoteFilterRepository
) {
    val observeNotes = ObserveNotesUseCase(noteRepository, noteFilterRepository)
    val createNoteUseCase = CreateNoteUseCase(noteRepository)
    val createNoteTextContentUseCase = CreateNoteTextContentUseCase(noteRepository)

    val observeNoteFilterState = ObserveNoteFilterState(noteFilterRepository)
    val updateNoteFilterState = UpdateNoteFilterStateUseCase(noteFilterRepository)
}