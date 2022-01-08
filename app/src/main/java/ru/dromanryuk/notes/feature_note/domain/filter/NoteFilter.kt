package ru.dromanryuk.notes.feature_note.domain.filter

import ru.dromanryuk.notes.feature_note.domain.model.Note

class NoteFilter(
    private val state: NoteFilterState
) {
    fun filter(notes: List<Note>) = notes
        .filterNotes()
        .sortNotes()

    private fun List<Note>.filterNotes() =
        NoteFilterMatcher(state.searchText, state.favouriteFilter).filterNotes(this)

    private fun List<Note>.sortNotes() =
        NoteSorter(state.sortingType).sortNote(this)
}