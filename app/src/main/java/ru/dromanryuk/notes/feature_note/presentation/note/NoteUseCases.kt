package ru.dromanryuk.notes.feature_note.presentation.note

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import ru.dromanryuk.notes.feature_note.domain.repository.NoteShareRepository
import ru.dromanryuk.notes.feature_note.domain.use_case.*
import javax.inject.Inject

class NoteUseCases @Inject constructor(
    noteRepository: NoteRepository,
    noteShareRepository: NoteShareRepository
) {
    val observeNoteUseCase = ObserveNoteUseCase(noteRepository)
    val updateNoteUseCase = UpdateNoteUseCase(noteRepository)
    val removeNoteUseCase = RemoveNoteUseCase(noteRepository)
    val shareNoteUseCase = ShareNoteUseCase(noteShareRepository)
    val toggleFavourite = ToggleFavouriteUseCase(noteRepository)
    val createNoteChecklistUseCase = CreateNoteCheckboxUseCase(noteRepository)
    val updateCheckboxUseCase = UpdateCheckboxUseCase(noteRepository)
}
