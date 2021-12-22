package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import ru.dromanryuk.notes.feature_note.data.model.NoteModel
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class CreateNoteUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(onNoteClick: suspend (id: Int) -> Unit) =
        withContext(Dispatchers.Default) {
            createNoteModel(
                NoteModel(
                    name = "",
                    isFavourite = false,
                    password = null,
                    creationTimestamp = Clock.System.now().toEpochMilliseconds(),
                    editingTimestamp = Clock.System.now().toEpochMilliseconds(),
                ),
                onNoteClick
            )
        }
    private suspend fun createNoteModel(noteModel: NoteModel, onNoteClick: suspend (id: Int) -> Unit) {
        withContext(Dispatchers.Default) {
            onNoteClick(noteRepository.addNote(noteModel))
        }
    }
}