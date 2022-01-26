package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dromanryuk.notes.feature_note.domain.filter.NoteSortingType
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultRadioButton
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialog
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultDialogTitle
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun SortingDialog(
    sortingType: NoteSortingType,
    onChangeSortingType: (NoteSortingType) -> Unit,
    onDismiss: () -> Unit
) {
    DefaultDialog(
        onDismissRequest = onDismiss,
        shapeRadius = 5
    ) {
        SortingDialogContent(
            sortingType = sortingType,
            onDismiss = onDismiss,
            onChangeSortingType = onChangeSortingType,
        )
    }
}

@Composable
private fun SortingDialogContent(
    sortingType: NoteSortingType,
    onChangeSortingType: (NoteSortingType) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DefaultDialogTitle("Сортировать по", 20.sp)
        SortingOptionGroup(
            sortingType = sortingType,
            onOptionSelected = {
                onChangeSortingType(it)
                onDismiss()
            }
        )
    }
}

@Composable
private fun SortingOptionGroup(
    sortingType: NoteSortingType,
    onOptionSelected: (NoteSortingType) -> Unit
) {
    Column(
        modifier = Modifier
            .selectableGroup()
            .height(IntrinsicSize.Min)
    ) {
        NoteSortingType.values().forEach {
            val isSelected = it == sortingType
            DefaultRadioButton(
                title = getSortingTypeTitle(sortingType = it),
                isSelected = isSelected,
                onSelected = { onOptionSelected(it) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun getSortingTypeTitle(sortingType: NoteSortingType) =
    when(sortingType) {
        NoteSortingType.Title -> "Название"
        NoteSortingType.RecentUsage -> "Недавно используемый"
        NoteSortingType.FrequentUsage -> "Часто используемый"
        NoteSortingType.CreationDate -> "Недавно созданный"
        NoteSortingType.EditingDate -> "Недавно изменённый"
    }


@Composable
@Preview
fun PreviewSortingDialog() {
    NotesTheme {
        SortingDialog(
            NoteSortingType.Title, {}, {}
        )
    }
}