package com.spaceflight.design.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.spaceflight.design.font16Sp
import com.spaceflight.design.textLetterSpacing
import com.spaceflight.design.textLineHeight

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = font16Sp(),
        lineHeight = textLineHeight(),
        letterSpacing = textLetterSpacing()
    )
)


