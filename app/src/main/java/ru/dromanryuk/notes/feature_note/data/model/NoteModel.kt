package ru.dromanryuk.notes.feature_note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.Password

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val isFavourite: Boolean,
    val password: String?,
    val creationTimestamp: Long,
    val editingTimestamp: Long,
)

fun Note.toNoteModel() = NoteModel(
    id = id,
    name = name,
    isFavourite = isFavourite,
    password = when (password) {
        is Password.NonePassword -> null
        is Password.CodePassword -> password.encryptedPassword
    },
    creationTimestamp = metadata.creationDateTime.toEpochMilliseconds(),
    editingTimestamp = metadata.editingDateTime.toEpochMilliseconds(),
)
