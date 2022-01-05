package ru.dromanryuk.notes.feature_note.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.dromanryuk.notes.feature_note.data.dao.NoteDao
import ru.dromanryuk.notes.feature_note.data.model.*
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override fun observeAll(): Flow<List<Note?>> =
        noteDao.observeAll()
            .map {
                it.map {
                    when {
                        getTextContentById(it.id) != null -> {
                            getTextContentById(it.id)?.toNote()
                        }
                        getChecklistContentById(it.id) != null -> {
                            getChecklistContentById(it.id)?.toNote()
                        }
                        else -> {
                            null
                        }
                    }
                }
            }

    override fun observeById(id: Int): Flow<Note?> =
        noteDao.observeById(id).map {
            when {
                getTextContentById(it.id) != null -> {
                    getTextContentById(it.id)!!.toNote()
                }
                getChecklistContentById(it.id) != null -> {
                    getChecklistContentById(it.id)!!.toNote()
                }
                else -> {
                    null
                }
            }
        }

    override suspend fun getTextContentById(noteId: Int): NoteTextEntity? {
        return noteDao.getNoteTextContentById(noteId)
    }

    override suspend fun getChecklistContentById(noteId: Int): NoteChecklistEntity? {
        return noteDao.getNoteChecklistContentById(noteId)
    }

    override suspend fun getById(id: Int): Note? {
        val note = noteDao.getNoteById(id)
        return when {
            getTextContentById(note.id) != null -> {
                getTextContentById(note.id)?.toNote()!!
            }
            getChecklistContentById(note.id) != null -> {
                getChecklistContentById(note.id)?.toNote()!!
            }
            else -> {
                null
            }
        }
    }

    override suspend fun addNote(note: NoteModel): Int {
        return noteDao.insertNote(note).toInt()
    }

    override suspend fun addText(text: NoteTextModel) {
        noteDao.insertTextContent(text)
    }

    override suspend fun addCheckbox(checkbox: Checkbox) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.updateNote(note.toNoteModel())
    }

    override suspend fun remove(noteId: Int) {
        noteDao.deleteNote(noteId)
        noteDao.deleteNoteTextContent(noteId)
    }

    override suspend fun updateNoteTextContent(noteId: Int, noteTextContent: String) =
        withContext(Dispatchers.IO) {
            noteDao.updateNoteTextContent(NoteTextModel(noteId, noteTextContent))
        }

    override suspend fun updateNoteChecklistContent() = withContext(Dispatchers.IO) {
        noteDao.updateNoteChecklistContent(NoteCheckboxModel(
            id = 0,
            noteId = 0,
            content = "",
            selected = false
        ))
    }
}