package ru.dromanryuk.notes.feature_note.domain.filter

import ru.dromanryuk.notes.core.SortingOrder

enum class NoteSortingType(val order: SortingOrder) {
    Title(SortingOrder.Ascending),
    RecentUsage(SortingOrder.Descending),
    FrequentUsage(SortingOrder.Descending),
    CreationDate(SortingOrder.Descending),
    EditingDate(SortingOrder.Descending)
}