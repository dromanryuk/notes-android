package ru.dromanryuk.notes.feature_note.domain.filter

data class NoteFilterState(
    val searchText: String = "",
    val favouriteFilter: FavouriteFilter = FavouriteFilter.All,
    val sortingType: NoteSortingType = NoteSortingType.Title
)
