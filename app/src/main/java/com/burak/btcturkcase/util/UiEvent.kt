package com.burak.btcturkcase.util

import com.burak.btcturkcase.navigation.NavigationType


sealed class UiEvent {
    data class Navigate<T>(val navigationType: NavigationType, val data: Map<String, T?>? = null) : UiEvent()
    data class ShowError(val throwable: Throwable?) : UiEvent()
}