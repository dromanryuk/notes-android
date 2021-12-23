package ru.dromanryuk.notes.feature_note.presentation.overview

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.CreateNoteTextContentUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.CreateNoteUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.ObserveNotesUseCase
import javax.inject.Inject

class OverviewUseCases @Inject constructor(
    noteRepository: NoteRepository
) {
    val observeNotes = ObserveNotesUseCase(noteRepository)
    val createNoteUseCase = CreateNoteUseCase(noteRepository)
    val createNoteTextContentUseCase = CreateNoteTextContentUseCase(noteRepository)
}