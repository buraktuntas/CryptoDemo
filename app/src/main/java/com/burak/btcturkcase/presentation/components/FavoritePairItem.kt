package com.burak.btcturkcase.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.presentation.theme.Blue
import com.burak.btcturkcase.presentation.theme.Grey


@Composable
fun FavoritePairItem(
    item: PairInfo,
    onItemClicked: (PairInfo) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Blue
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(6.dp)
            .width(86.dp)
            .clickable {
                onItemClicked(item)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = item.pair,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.last.toString(),
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            DailyPercentText(item.dailyPercent ?: 0.0, MaterialTheme.typography.bodyMedium)

        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true, backgroundColor = 0xFF09101B)
@Composable
fun PreviewFavoritePairItem() {
    FavoritePairItem(
        item = PairInfo(
            pair = "BTCTRY",
            pairNormalized = "BTC_TRY",
            volume = 304.41,
            dailyPercent = -1.02,
            last = 47500.0,
            numeratorSymbol = "BTC",
            isFavorite = false
        ),
        onItemClicked = {}
    )
}


