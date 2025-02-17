package com.app.twittercounter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Base TextStyle with common properties for reuse
private val baseTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    letterSpacing = 0.sp
)

val Typography = Typography(
    headlineLarge = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 37.sp
    ),
    titleLarge = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    titleMedium = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    bodyLarge = baseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = baseTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = baseTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
