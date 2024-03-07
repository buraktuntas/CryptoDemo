package com.burak.btcturkcase.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.presentation.theme.Grey
import com.burak.btcturkcase.presentation.theme.Orange
import com.burak.btcturkcase.presentation.theme.White


@Composable
fun PairListItem(
    item: PairInfo, onItemClicked: (PairInfo) -> Unit, onFavoriteClick: () -> Unit
) {

    Row(
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp, start = 18.dp, end = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            tint = if (item.isFavorite ?: false) Orange else Grey,
            contentDescription = "Favorite Icon",
            modifier = Modifier
                .clickable { onFavoriteClick() }
        )
        Spacer(modifier = Modifier.width(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 2.dp)
                .clickable { onItemClicked(item) }
        ) {
            Text(
                text = item.pair,
                color = White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,

                )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = item.last.toString(),
                        color = White,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    DailyPercentText(item.dailyPercent?:0.0, MaterialTheme.typography.bodySmall)
                }

                VolumeText(item.volume?:0.0, item.numeratorSymbol.orEmpty(), MaterialTheme.typography.bodySmall)

            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true, backgroundColor = 0xFF09101B)
@Composable
fun PreviewPairListItem() {
    PairListItem(
        item = PairInfo(
            pair = "BTCTRY",
            pairNormalized = "BTC_TRY",
            volume = 304.41,
            dailyPercent = -1.02,
            last = 47500.0,
            numeratorSymbol = "BTC",
            isFavorite = false
        ),
        onFavoriteClick = {},
        onItemClicked = {},
    )
}
