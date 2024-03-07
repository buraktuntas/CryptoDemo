package com.burak.btcturkcase.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.btcturkcase.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    protected fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    protected fun handleException(exception: Throwable) {
        sendUiEvent(UiEvent.ShowError(exception))
    }
}