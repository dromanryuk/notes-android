package ru.dromanryuk.notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.dromanryuk.notes.ui.theme.attr.Elevations
import ru.dromanryuk.notes.ui.theme.attr.LocalElevations
import ru.dromanryuk.notes.ui.theme.attr.Shapes
import ru.dromanryuk.notes.ui.theme.attr.Typography

private val YellowDarkColorPalette = darkColors(
    primary = YellowPrimary,
    secondary = YellowSecondary,
    secondaryVariant = YellowSecondaryVariant,
    background = YellowBackground,
    surface = YellowSurface
)

private val YellowLightColorPalette = lightColors(
    primary = YellowPrimary,
    secondary = YellowSecondary,
    secondaryVariant = YellowSecondaryVariant,
    background = YellowBackground,
    surface = YellowSurface
)

@Composable
fun NotesTheme(
    lightColorPalette: Colors = YellowLightColorPalette,
    darkColorPalette: Colors = YellowDarkColorPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    statusBarColor: Color = NotesTheme.colors.primary,
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUiController.setStatusBarColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = Color.Black)
        darkColorPalette
    } else {
        systemUiController.setStatusBarColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = Color.Black)
        lightColorPalette
    }
    CompositionLocalProvider(
        LocalElevations provides Elevations(card = 8.dp)
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
}

object NotesTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors
}

enum class ThemeType {
    ORANGE,
    YELLOW,
    LIME,
    GREEN,
    TURQUOISE
}