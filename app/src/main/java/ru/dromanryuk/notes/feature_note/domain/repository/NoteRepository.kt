package ru.dromanryuk.notes.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.model.*
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.Note

interface NoteRepository {

    fun observeAll(): Flow<List<Note>>

    fun observeById(id: Int): Flow<Note?>

    suspend fun getTextContentById(noteId: Int): NoteTextModel?

    suspend fun getChecklistContentById(noteId: Int): List<NoteCheckboxModel>?

    suspend fun getNoteById(id: Int): Note

    suspend fun getCheckboxById(id: Int): Checkbox

    suspend fun addNote(note: NoteModel): Int

    suspend fun addText(text: NoteTextModel)

    suspend fun addCheckbox(checkbox: NoteCheckboxModel)

    suspend fun updateNote(note: Note)

    suspend fun updateCheckbox(noteId: Int, checkbox: Checkbox)

    suspend fun remove(noteId: Int)

    suspend fun updateNoteTextContent(noteId: Int, noteTextContent: String)

    suspend fun updateNoteChecklistContent()
}