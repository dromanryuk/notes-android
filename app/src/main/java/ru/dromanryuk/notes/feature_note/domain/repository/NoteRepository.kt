package ru.dromanryuk.notes.feature_note.domain.repository

import androidx.compose.foundation.text.InlineTextContent
import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.model.*
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import javax.inject.Inject

interface NoteRepository {

    fun observeAll(): Flow<List<Note?>>

    fun observeById(id: Int): Flow<Note?>

    suspend fun getTextContentById(noteId: Int): NoteTextEntity?

    suspend fun getChecklistContentById(noteId: Int): NoteChecklistEntity?

    suspend fun getById(id: Int): Note?

    suspend fun addNote(note: NoteModel): Int

    suspend fun addText(text: NoteTextModel)

    suspend fun addCheckbox(checkbox: Checkbox)

    suspend fun updateNote(note: Note)

    suspend fun updateNoteTextContent(noteId: Int, noteTextContent: String)

    suspend fun updateNoteChecklistContent()
}