package com.example.edietask.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val PriorityLowBg = Color(0xFFE8F5E9)
val PriorityLowText = Color(0xFF2E7D32)

val PriorityMediumBg = Color(0xFFFFF3E0)
val PriorityMediumText = Color(0xFFEF6C00)

val PriorityHighBg = Color(0xFFFFEBEE)
val PriorityHighText = Color(0xFFC62828)

val ListRed = Color(0xFFE57373)
val ListGreen = Color(0xFF81C784)
val ListBlue = Color(0xFF64B5F6)
val ListOrange = Color(0xFFFFB74D)
val ListPurple = Color(0xFFBA68C8)
val ListTeal = Color(0xFF4DB6AC)
val ListGrey = Color(0xFF90A4AE)
val ListYellow = Color(0xFFFFCA28)

val ListColorPalette = listOf(
    ListRed, ListGreen, ListBlue, ListOrange,
    ListPurple, ListTeal, ListGrey, ListYellow
)

fun Color.toHexCode(): String {
    return String.format("#%06X", 0xFFFFFF and this.toArgb())
}
