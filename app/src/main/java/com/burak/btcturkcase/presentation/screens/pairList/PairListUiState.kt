package com.burak.btcturkcase.presentation.screens.pairList

import com.burak.btcturkcase.data.local.entity.PairInfo

data class PairListUiState(
    val isLoading: Boolean = false,
    val allList: List<PairInfo> = arrayListOf(),
    val favoriteList: List<PairInfo> = arrayListOf(),
)