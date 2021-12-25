package ru.dromanryuk.notes.feature_note.presentation.note.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit,
    placeholder: String,
    maxLines: Int
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = MaterialTheme.colors.surface,
            fontSize = fontSize,
            fontFamily = FontFamily.SansSerif
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colors.onBackground,
                fontSize = fontSize,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.secondary,
            textColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.background,
            focusedIndicatorColor = MaterialTheme.colors.background,
        ),
        maxLines = maxLines
    )
}