package ru.dromanryuk.notes.feature_note.presentation.overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dromanryuk.notes.feature_note.presentation.components.DefaultIconButton
import ru.dromanryuk.notes.ui.theme.NotesTheme

@Composable
fun OverviewTopAppBar(onSortingClicked: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
                .clip(RoundedCornerShape(50.dp))
        ) {
            TextField(
                modifier = Modifier
                    .clickable { /* переход в экран поиска заметок */ }
                    .background(MaterialTheme.colors.primary)
                    .fillMaxSize(),
                value = "",
                onValueChange = {  },
                placeholder = {
                    Text(
                        text = "Искать в заметках",
                        color = MaterialTheme.colors.surface,
                    )
                },
                leadingIcon = {
                    DefaultIconButton(Icons.Filled.Settings, MaterialTheme.colors.surface) {  }
                },
                trailingIcon = {
                    Row {
                        DefaultIconButton(Icons.Outlined.Dashboard, MaterialTheme.colors.surface) {  }
                        DefaultIconButton(Icons.Filled.Menu, MaterialTheme.colors.surface) {
                            onSortingClicked()
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun OverviewTopAppBarPreview() {
    NotesTheme {
        OverviewTopAppBar {}
    }
}