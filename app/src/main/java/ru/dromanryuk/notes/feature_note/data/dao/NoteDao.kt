package ru.dromanryuk.notes.feature_note.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.model.*

@Dao
interface NoteDao {

    @Query("select * from NoteModel")
    fun observeAll(): Flow<List<NoteModel>>

    @Query("select * from NoteModel where id = :id")
    fun observeById(id: Int): Flow<NoteModel>

    @Insert
    fun insertNote(note: NoteModel): Long

    @Update
    fun updateNote(note: NoteModel)

    @Update
    fun updateNoteTextContent(textContent: NoteTextModel)

    @Update
    fun updateNoteChecklistContent(checklistContent: NoteCheckboxModel)

    @Insert
    fun insertTextContent(noteTextModel: NoteTextModel)

    @Insert
    fun insertCheckboxContent(noteTextModel: NoteCheckboxModel)

    @Query("select * from NoteModel where id = :noteId")
    suspend fun getNoteTextContentById(noteId: Int): NoteTextEntity?

    @Query("select * from NoteModel where id = :noteId")
    suspend fun getNoteChecklistContentById(noteId: Int): NoteChecklistEntity?

    @Query("select * from NoteModel where id = :noteId")
    suspend fun getNoteById(noteId: Int): NoteModel

}