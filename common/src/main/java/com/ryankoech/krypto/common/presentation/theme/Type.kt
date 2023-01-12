package com.ryankoech.krypto.common.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.R

val CircularStd = FontFamily(
    Font(R.font.circular_std_book, weight = FontWeight.Medium),
    Font(R.font.circular_std_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    h1 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    h2 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    h3 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    h4 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    ),
)