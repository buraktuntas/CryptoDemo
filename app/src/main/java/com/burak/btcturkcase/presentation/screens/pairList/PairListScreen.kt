package com.burak.btcturkcase.presentation.screens.pairList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.presentation.components.FavoritePairItem
import com.burak.btcturkcase.presentation.components.PairListItem
import com.burak.btcturkcase.presentation.theme.BlueDark
import com.burak.btcturkcase.presentation.theme.Grey
import com.burak.btcturkcase.presentation.theme.White
import com.burak.btcturkcase.util.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun PairListScreen(
    uiState: PairListUiState,
    uiEvent: Flow<UiEvent>,
    onEvent: (PairListUiEvent) -> Unit,
    onUiEvent: (UiEvent) -> Unit,
) {

    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            onUiEvent(event)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueDark)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (uiState.allList.any { it.isFavorite ?: false }) {

                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "Favorites",
                        color = White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        item {
                            Spacer(modifier = Modifier.width(18.dp))
                        }

                        uiState.allList.filter { it.isFavorite ?: false }.let { itemList ->
                            items(itemList.size) { itemIndex ->
                                FavoritePairItem(
                                    item = itemList[itemIndex],
                                    onItemClicked = {
                                        onEvent(
                                            PairListUiEvent.OnPairItemClicked(
                                                item = itemList[itemIndex]
                                            )
                                        )
                                    },
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            if (uiState.allList.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "Pairs",
                        color = White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                uiState.allList.let { itemList ->
                    items(itemList.size) { itemIndex ->
                        itemList[itemIndex].let { item ->
                            PairListItem(
                                item = item,
                                onItemClicked = {
                                    onEvent(
                                        PairListUiEvent.OnPairItemClicked(
                                            item = item
                                        )
                                    )
                                },
                                onFavoriteClick = {
                                    onEvent(
                                        PairListUiEvent.OnFavoriteClicked(
                                            item = item
                                        )

                                    )
                                }
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(0.5.dp)
                                    .background(color = Grey)
                            )
                        }
                    }
                }
            }

        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun PreviewPairListScreen() {
    val mockPairInfoList = listOf(
        PairInfo(
            pair = "BTCTRY",
            pairNormalized = "BTC_TRY",
            volume = 304.41,
            dailyPercent = -1.02,
            last = 47500.0,
            numeratorSymbol = "BTC",
            isFavorite = true
        ),
        PairInfo(
            pair = "BTCTRY",
            pairNormalized = "BTC_TRY",
            volume = 304.41,
            dailyPercent = -1.02,
            last = 47500.0,
            numeratorSymbol = "BTC",
            isFavorite = false
        ),
        PairInfo(
            pair = "BTCTRY",
            pairNormalized = "BTC_TRY",
            volume = 304.41,
            dailyPercent = -1.02,
            last = 47500.0,
            numeratorSymbol = "BTC",
            isFavorite = true
        ),
    )

    PairListScreen(
        uiState = PairListUiState(
            allList = mockPairInfoList
        ),
        uiEvent = emptyFlow(),
        onEvent = { },
        onUiEvent = { },
    )
}