package com.burak.btcturkcase.presentation.screens.pairChart

data class PairChartUiState (
    val isLoading: Boolean = false,
    val chartData: List<Pair<Long, Float>> = emptyList(),
    val pair: String = "",
)