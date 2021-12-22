package ru.dromanryuk.notes.feature_note.domain.filter

import ru.dromanryuk.notes.feature_note.domain.model.Note

sealed class FavouriteFilter {
    abstract fun matches(note: Note): Boolean

    object All : FavouriteFilter() {
        override fun matches(note: Note): Boolean = true
    }

    object Favourite : FavouriteFilter() {
        override fun matches(note: Note): Boolean = note.isFavourite
    }
}
