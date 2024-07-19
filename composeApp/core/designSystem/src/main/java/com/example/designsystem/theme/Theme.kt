package com.example.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BlueGrey11,
    secondary = Green54,
    tertiary = DarkGreen15,
    background = BlueGrey11,
    onBackground = BlueGrey11,
    onPrimary = Green67,
    onSecondary = White,
    surfaceTint = Grey96,
    inversePrimary = White,
    secondaryContainer = Grey14,
    inverseSurface = Grey22,
    onTertiary = White
)

private val LightColorScheme = lightColorScheme(
    primary = Green67,
    secondary = Green54,
    tertiary = DarkGreen15,
    background = White,
    onBackground = BlueGrey11,
    onPrimary = Green67,
    onSecondary = BlueGrey11,
    surfaceTint = Grey96,
    inversePrimary = Color.Black,
    secondaryContainer = Grey98,
    inverseSurface = Grey96,
    onTertiary = Grey38

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun FinaxisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}