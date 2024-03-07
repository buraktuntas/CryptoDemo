package com.burak.btcturkcase.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun VolumeText(volume: Double, numeratorSymbol: String, style: TextStyle) {

    val formattedVolume = BigDecimal(volume).setScale(4, RoundingMode.HALF_EVEN)
        .stripTrailingZeros().toPlainString()

    Text(
        text = "$formattedVolume $numeratorSymbol",
        color = Color.Gray,
        maxLines = 1,
        style = style
    )
}