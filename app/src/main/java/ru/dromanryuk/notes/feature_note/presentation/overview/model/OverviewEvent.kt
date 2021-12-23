package ru.dromanryuk.notes.feature_note.presentation.overview.model

import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.filter.FavouriteFilter
import ru.dromanryuk.notes.feature_note.domain.filter.NoteSortingType

sealed class OverviewEvent {
    object CreateNote : OverviewEvent()

    data class SearchTextChanged(val searchText: String) : OverviewEvent()
    data class FavouriteFilterChanged(val favouriteFilter: FavouriteFilter) : OverviewEvent()

    data class UpdateSortDialogVisibility(val visibility: UiComponentVisibility) : OverviewEvent()
    data class SortingTypeChanged(val sortingType: NoteSortingType) : OverviewEvent()
}
