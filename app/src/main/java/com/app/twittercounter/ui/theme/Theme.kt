package com.app.twittercounter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define shared color scheme values to avoid duplication
private val primaryColor = Blue
private val secondaryColor = BlueGrey80
private val tertiaryColor = Green
private val errorColor = Red
private val tertiaryContainerColor = Black
private val backgroundColor = White
private val outlineColor = Grey
private val surfaceColor = White

// Dark theme color scheme
private val DarkColorScheme = darkColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    error = errorColor,
    tertiaryContainer = tertiaryContainerColor,
    background = backgroundColor,
    outline = outlineColor,
    surface = surfaceColor
)

// Light theme color scheme
private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    error = errorColor,
    tertiaryContainer = tertiaryContainerColor,
    background = backgroundColor,
    outline = outlineColor,
    surface = surfaceColor
)


@Composable
fun TwitterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Choose color scheme based on the theme preference
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}