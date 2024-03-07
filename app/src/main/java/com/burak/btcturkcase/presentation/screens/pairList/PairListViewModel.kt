package com.burak.btcturkcase.presentation.screens.pairList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.btcturkcase.base.BaseViewModel
import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.data.local.PairDataRepository
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.domain.use_case.GetPairListUseCase
import com.burak.btcturkcase.navigation.NavigationType
import com.burak.btcturkcase.navigation.Route
import com.burak.btcturkcase.util.ExceptionHandler
import com.burak.btcturkcase.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PairListViewModel @Inject constructor(
    private val getPairListUseCase: GetPairListUseCase,
    private val pairDataRepository: PairDataRepository
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(PairListUiState())
    val uiState: StateFlow<PairListUiState> = _uiState.asStateFlow()

    init {
        callGetPairList()
    }

    fun onEvent(event: PairListUiEvent) {
        when (event) {
            is PairListUiEvent.OnPairItemClicked -> {
                onItemClick(event.item)
            }

            is PairListUiEvent.OnFavoriteClicked -> {
                updateFavoriteItem(event.item)
            }

        }
    }

    private fun onItemClick(pairInfo: PairInfo) {
        viewModelScope.launch(ExceptionHandler.handler) {
            sendUiEvent(
                UiEvent.Navigate(
                    navigationType = NavigationType.Navigate(Route.PairChartScreen),
                    data = mapOf(
                        "pair" to pairInfo.pair
                    )
                )
            )
        }
    }

    fun callGetPairList(symbol: String = "USDT") {
        viewModelScope.launch(ExceptionHandler.handler) {
            updatePageLoading(true)
            getPairListUseCase(
                symbol = symbol,
                onResult = { result ->
                    when (result) {
                        is ResultState.Success -> {
                            val newDataList = result.data
                            pairDataRepository.allList().first().let { existingList ->
                                val updatedList = newDataList.map { newItem ->
                                    val existingItem = existingList.find { it.pair == newItem.pair }
                                    newItem.copy(isFavorite = existingItem?.isFavorite ?: false)
                                }
                                pairDataRepository.addAll(updatedList)
                                updatePairList(updatedList)
                            }
                            updatePageLoading(false)
                        }
                        is ResultState.Error -> {
                            handleException(result.exception)
                            updatePageLoading(false)
                        }
                        else -> Unit
                    }
                }
            )
        }
    }

    private fun updatePairList(allList: List<PairInfo>) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiState.update { currentState ->
                currentState.copy(
                    allList = allList
                )
            }
        }
    }

    fun updateFavoriteItem(item: PairInfo) {
        viewModelScope.launch {
            val updatedItem = withContext(Dispatchers.IO) {
                val updated = item.copy(isFavorite = item.isFavorite?.not() ?: false)
                pairDataRepository.updatePair(updated)
                updated
            }

            _uiState.value = _uiState.value.let { currentState ->
                val updatedList = currentState.allList.map { if (it.pair == updatedItem.pair) updatedItem else it }
                currentState.copy(allList = updatedList)
            }
        }
    }

    fun updatePageLoading(isLoading: Boolean) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = isLoading
                )
            }
        }
    }
}
