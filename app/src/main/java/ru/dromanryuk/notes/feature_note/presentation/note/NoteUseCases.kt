package ru.dromanryuk.notes.feature_note.presentation.note

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.ObserveNoteUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.RemoveNoteUseCase
import ru.dromanryuk.notes.feature_note.domain.use_case.UpdateNoteUseCase
import javax.inject.Inject

class NoteUseCases @Inject constructor(noteRepository: NoteRepository) {
    val observeNoteUseCase = ObserveNoteUseCase(noteRepository)
    val updateNoteUseCase = UpdateNoteUseCase(noteRepository)
    val removeNoteUseCase = RemoveNoteUseCase(noteRepository)
}
