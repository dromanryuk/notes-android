package ru.dromanryuk.notes.feature_note.domain.filter

import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent

class NoteFilterMatcher(
    searchText: String,
    private val favouriteFilter: FavouriteFilter,
) {
    private val searchText = searchText.trim()

    fun filterNotes(notes: List<Note>) = notes.filter { matches(it) }

    private fun matches(note: Note) =
        favouriteFilter.matches(note) && matchesSearchText(note)

    private fun matchesSearchText(note: Note) = with(note) {
        searchText.isBlank() ||
                name.contains(searchText, ignoreCase = true) ||
                when (content) {
                    is NoteContent.TextNote -> content.text.contains(searchText, ignoreCase = true)
                    is NoteContent.ChecklistNote -> containsInChecklist(
                        content.checkboxes, searchText)
                }

    }

    private fun containsInChecklist(checkboxes: List<Checkbox>, searchText: String): Boolean {
        checkboxes.forEach {
            return it.content.contains(searchText, ignoreCase = true)
        }
        return false
    }
}