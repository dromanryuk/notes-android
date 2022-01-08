package ru.dromanryuk.notes.feature_note.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilter
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.repository.NoteFilterRepository
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class ObserveNotesUseCase(
    private val noteRepository: NoteRepository,
    private val noteFilterRepository: NoteFilterRepository
) {
    operator fun invoke() = observeFilteredNotes()
        .flowOn(Dispatchers.Default)
        .conflate()

    private fun createResultFlow() = flow<List<Note>> {
        observeFilteredNotes()
    }

    private fun observeFilteredNotes() = combine(
        noteRepository.observeAll(),
        observeNotesFilter()
    ) { notes, noteFilter ->
        noteFilter.filter(notes as List<Note>)
    }

    private fun observeNotesFilter() = noteFilterRepository.observeNoteFilterState()
        .map { NoteFilter(it) }
}