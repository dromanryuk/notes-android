package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.feature_note.domain.model.Checkbox

data class ChecklistState(
    val selectedList: List<Checkbox> = emptyList(),
    val unselectedList: List<Checkbox> = emptyList(),
)
