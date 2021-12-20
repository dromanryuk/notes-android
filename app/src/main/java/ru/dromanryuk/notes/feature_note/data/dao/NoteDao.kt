package ru.dromanryuk.notes.feature_note.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.model.NoteCheckboxModel
import ru.dromanryuk.notes.feature_note.data.model.NoteChecklistEntity
import ru.dromanryuk.notes.feature_note.data.model.NoteModel
import ru.dromanryuk.notes.feature_note.data.model.NoteTextModel

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: NoteModel)

    @Insert
    fun insertTextContent(noteTextModel: NoteTextModel)

    @Insert
    fun insertCheckboxContent(noteTextModel: NoteCheckboxModel)

//    @Insert
//    fun insertChecklistContent(noteChecklistEntity: NoteChecklistEntity)

    @Query("select * from NoteModel")
    fun getNotes(): Flow<List<NoteModel>>


}