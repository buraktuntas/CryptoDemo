package com.burak.btcturkcase.presentation.screens.pairList

import com.burak.btcturkcase.data.local.entity.PairInfo

sealed class PairListUiEvent {
    data class OnPairItemClicked(val item: PairInfo) : PairListUiEvent()
    data class OnFavoriteClicked(val item: PairInfo) : PairListUiEvent()
}