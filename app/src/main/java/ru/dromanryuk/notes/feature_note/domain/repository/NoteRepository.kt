package ru.dromanryuk.notes.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.model.NoteModel
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import javax.inject.Inject

interface NoteRepository {

    fun observeAll(): Flow<List<NoteModel>>

    suspend fun addNote(note: NoteModel)

    suspend fun addText(text: String)

    suspend fun addCheckbox(checkbox: Checkbox)

//    suspend fun addContent(content: NoteContent)
}