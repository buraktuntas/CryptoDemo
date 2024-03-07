package com.burak.btcturkcase.presentation.screens.pairChart

import androidx.lifecycle.viewModelScope
import com.burak.btcturkcase.base.BaseViewModel
import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.domain.use_case.GetPairChartUseCase
import com.burak.btcturkcase.navigation.NavigationType
import com.burak.btcturkcase.util.ExceptionHandler
import com.burak.btcturkcase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PairChartViewModel @Inject constructor(
    private val getPairChartUseCase: GetPairChartUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(PairChartUiState())
    val uiState: StateFlow<PairChartUiState> = _uiState.asStateFlow()

    fun onEvent(event: PairChartUiEvent) {
        when (event) {
            is PairChartUiEvent.OnBackClicked -> {
                onBackClick()
            }

        }
    }

    private fun onBackClick() {
        sendNavigationEvent(NavigationType.PopBack)
    }

    private fun sendNavigationEvent(navigationType: NavigationType, data: Map<String, Any?>? = emptyMap()) {
        viewModelScope.launch {
            sendUiEvent(UiEvent.Navigate(navigationType, data))

        }
    }

    fun fetchChartData() {
        viewModelScope.launch(ExceptionHandler.handler) {
            updatePageLoading(true)
            val (now, from, to) = calculateTimeRange()
            getPairChartUseCase(
                symbol = uiState.value.pair,
                resolution = "1D",
                from = from,
                to = to,
                onResult = {
                    when (it) {
                        is ResultState.Success -> {
                            updatePageLoading(false)
                            val chartDataList = it.data
                            val chartDataPairs = chartDataList.map { chartData ->
                                Pair(chartData.time, chartData.close)
                            }
                            _uiState.update {
                                it.copy(
                                    chartData = chartDataPairs,
                                )
                            }
                        }

                        is ResultState.Error -> {
                            handleException(it.exception)
                            updatePageLoading(false)

                        }

                        else -> {}
                    }
                }
            )
        }
    }

    fun updatePairAndFetchChartData(pair: String) {
        _uiState.update { it.copy(pair = pair) }
        fetchChartData()
    }

    private fun updatePageLoading(isLoading: Boolean) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = isLoading
                )
            }
        }
    }

    fun calculateTimeRange(): Triple<Long, Long, Long> {
        val now = System.currentTimeMillis() / 1000
        val fifteenDaysInSeconds = 15 * 24 * 60 * 60 // 15 günü saniye cinsinden hesapla
        val from = now - fifteenDaysInSeconds
        val to = now
        return Triple(now, from, to)
    }

}



