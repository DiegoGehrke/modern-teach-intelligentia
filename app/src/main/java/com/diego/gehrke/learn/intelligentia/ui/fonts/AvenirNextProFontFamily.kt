package com.diego.gehrke.learn.intelligentia.ui.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.diego.gehrke.learn.intelligentia.R

object AvenirNextProFontFamily {
    val avenirFont = FontFamily(
        Font(resId = R.font.avenir_next_pro_regular, weight = FontWeight.Normal),
        Font(resId = R.font.avenir_next_pro_bold, weight = FontWeight.Bold),
        Font(resId = R.font.avenir_text_demibold, weight = FontWeight.SemiBold)
    )
}