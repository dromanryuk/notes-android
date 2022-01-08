package ru.dromanryuk.notes.feature_note.domain.filter

import ru.dromanryuk.notes.core.SortingOrder
import ru.dromanryuk.notes.feature_note.domain.model.Note

class NoteSorter(
    private val type: NoteSortingType
) {
    fun sortNote(notes: List<Note>) = notes.sortedWith(createComparator())

    private fun createComparator(): Comparator<Note> {
        val selector = createCompareSelectorByType()
        return when (type.order) {
            SortingOrder.Ascending -> compareBy(selector)
            SortingOrder.Descending -> compareByDescending(selector)
        }
    }

    private fun createCompareSelectorByType(): ((Note) -> Comparable<*>) {
        return when (type) {
            NoteSortingType.Title -> { it -> it.name }
            NoteSortingType.RecentUsage -> TODO() //{ it -> UsageHistory(it.usages).lastUsageDateTime }
            NoteSortingType.FrequentUsage -> TODO() //{ it -> UsageHistory(it.usages).usagesCount }
            NoteSortingType.CreationDate -> { it -> it.metadata.creationDateTime }
            NoteSortingType.EditingDate -> { it -> it.metadata.editingDateTime }
        }
    }
}