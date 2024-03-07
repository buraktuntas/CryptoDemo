package com.burak.btcturkcase.presentation.screens.pairChart

sealed class PairChartUiEvent {
    data object OnBackClicked : PairChartUiEvent()
}