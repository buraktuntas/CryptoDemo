package com.burak.btcturkcase.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.burak.btcturkcase.presentation.theme.Green
import com.burak.btcturkcase.presentation.theme.Red

@Composable
fun DailyPercentText(dailyPercent: Double, style: TextStyle) {

    val text = String.format("%.2f", kotlin.math.abs(dailyPercent))

    val color = when {
        dailyPercent < 0 -> Red
        dailyPercent > 0 -> Green
        else -> Color.Gray
    }
    Text(
        text = "%$text",
        color = color,
        maxLines = 1,
        style = style
    )
}