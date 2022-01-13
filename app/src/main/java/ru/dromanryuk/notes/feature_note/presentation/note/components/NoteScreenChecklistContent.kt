package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteContentState
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteState
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun NoteScreenChecklistContent(
    state: NoteState,
    onTitleChange: (String) -> Unit,
    onCheckboxChange: (Checkbox) -> Unit,
    onAddCheckbox: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .navigationBarsWithImePadding()
            .background(MaterialTheme.colors.background)
    ) {
        item {
            DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.titleState,
            onValueChange = onTitleChange,
            fontSize = 20.sp,
            placeholder = "Название",
            maxLines = 1
            )
        }
        items(state.contentState.checklist!!.unselectedList) {
            ChecklistItem(it, onCheckboxChange)
        }
        item {
            NewItemButton(onAddCheckbox)
        }
        item {
            if (state.contentState.checklist!!.selectedList.isNotEmpty()) {
                SelectedItemsCounter()
            }
        }
        items(state.contentState.checklist!!.selectedList) {
            ChecklistItem(it, onCheckboxChange)
        }
    }
}

@Composable
private fun NewItemButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick() }
            )
            .padding(start = 20.dp, end = 20.dp, bottom = 7.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Filled.Add,
//            modifier = Modifier
//                .size(33.dp),
            contentDescription = "",
            tint = MaterialTheme.colors.surface
        )
        Text(
            text = "Новый пункт",
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colors.surface,
            fontSize = 19.sp,
        )
    }
}

@Composable
private fun SelectedItemsCounter() {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 7.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Filled.KeyboardArrowDown,
//            modifier = Modifier
//                .size(33.dp),
            contentDescription = "",
            tint = MaterialTheme.colors.surface
        )
        Text(
            text = "Отмеченные пункты",
            //modifier = Modifier.padding(bottom = 3.dp),
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colors.surface,
            fontSize = 19.sp,
        )
    }
}

@Composable
private fun ChecklistItem(
    checkbox: Checkbox,
    onCheckboxChange: (Checkbox) -> Unit,
) {
    val deleteButton = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            modifier = Modifier
                .padding(end = 15.dp),
            checked = checkbox.selected,
            onCheckedChange = { onCheckboxChange(checkbox.copy(selected = it)) },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary,
                uncheckedColor = MaterialTheme.colors.surface,
                checkmarkColor = MaterialTheme.colors.background
            )
        )
        DefaultTextField(
            modifier = Modifier,
            value = "",
            onValueChange = { onCheckboxChange(checkbox.copy(content = it)) },
            fontSize = 16.sp,
            placeholder = "",
            maxLines = Int.MAX_VALUE)
    }
}

@Preview
@Composable
private fun NoteScreenChecklistContentPreview() {
    NotesTheme {
        NoteScreenChecklistContent(
            state = NoteState(contentState = NoteContentState.Checklist()),
            onTitleChange = { },
            onCheckboxChange = { },
            onAddCheckbox = { }
        )
    }
}