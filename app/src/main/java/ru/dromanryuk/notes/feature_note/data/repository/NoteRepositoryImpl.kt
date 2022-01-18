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
    override fun observeAll(): Flow<List<Note>> =
        noteDao.observeAll()
            .map { notes ->
                notes.map {
                    generateNote(it)
                }
            }

    private suspend fun generateNote(
        noteModel: NoteModel,
    ): Note {
        return when {
            getTextContentById(noteModel.id) != null -> {
                val textContent = getTextContentById(noteModel.id)
                NoteTextEntity(noteModel, textContent).toNote()
            }
            getChecklistContentById(noteModel.id) != null -> {
                val checklist = getChecklistContentById(noteModel.id)
                NoteChecklistEntity(noteModel, checklist).toNote()
            }
            else -> {
                error("Note is null")
            }
        }
    }

    override fun observeById(id: Int): Flow<Note?> =
        noteDao.observeById(id).map {
            it?.let {generateNote(it) }
        }

    override suspend fun getTextContentById(noteId: Int): NoteTextModel? {
        return noteDao.getNoteTextContentById(noteId)
    }

    override suspend fun getChecklistContentById(noteId: Int): List<NoteCheckboxModel>? {
        return noteDao.getNoteChecklistContentById(noteId)
    }

    override suspend fun getNoteById(id: Int): Note {
        val note = noteDao.getNoteById(id)
        return generateNote(note)
    }

    override suspend fun getCheckboxById(id: Int): Checkbox {
        return noteDao.getCheckboxById(id).toCheckbox()
    }

    override suspend fun getPasswordById(noteId: Int): String {
        val password = noteDao.getPasswordById(noteId)
        return if (password.isNullOrEmpty()) "" else password
    }

    override suspend fun addNote(note: NoteModel): Int {
        return noteDao.insertNote(note).toInt()
    }

    override suspend fun addText(text: NoteTextModel) {
        noteDao.insertTextContent(text)
    }

    override suspend fun addCheckbox(checkbox: NoteCheckboxModel) {
        noteDao.insertCheckboxContent(checkbox)
    }

    override suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.updateNote(note.toNoteModel())
    }

    override suspend fun updateCheckbox(noteId: Int, checkbox: Checkbox) = withContext(Dispatchers.IO) {
        noteDao.updateCheckbox(checkbox.toNoteCheckboxModel(noteId))
    }

    override suspend fun removeNote(noteId: Int) {
        noteDao.deleteNote(noteId)
        noteDao.deleteNoteTextContent(noteId)
        noteDao.deleteNoteChecklistContent(noteId)
    }

    override suspend fun removePassword(note: NoteModel) = withContext(Dispatchers.IO) {
        noteDao.updateNote(note)
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