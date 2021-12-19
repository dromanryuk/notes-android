package ru.dromanryuk.notes.feature_note.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dromanryuk.notes.feature_note.data.dao.NoteDao
import ru.dromanryuk.notes.feature_note.data.model.NoteCheckboxModel
import ru.dromanryuk.notes.feature_note.data.model.NoteModel
import ru.dromanryuk.notes.feature_note.data.model.NoteTextModel

@Database(
    entities = [
        NoteModel::class,
        NoteTextModel::class,
        NoteCheckboxModel::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DB_NAME = "note.db"
    }
}