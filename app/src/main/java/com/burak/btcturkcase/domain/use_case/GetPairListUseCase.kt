package com.burak.btcturkcase.domain.use_case

import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.data.local.PairDataRepository
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.data.remote.dto.pairList.Data
import com.burak.btcturkcase.domain.repository.PairRepository
import kotlinx.coroutines.flow.catch

class GetPairListUseCase(
    private val pairRepository: PairRepository,
    private val pairDataRepository: PairDataRepository
) {

    suspend operator fun invoke(symbol: String, onResult: suspend (ResultState<List<PairInfo>>) -> Unit) {
        pairRepository.getPairList(symbol)
            .catch { exception ->
                onResult(ResultState.Error(exception))
            }
            .collect { rocketsDtoList ->
                val rocketInfoList = convertResponseToUI(rocketsDtoList)
                pairDataRepository.addAll(rocketInfoList)
                onResult(ResultState.Success(rocketInfoList))
            }
    }

    private fun convertResponseToUI(response: List<Data>): List<PairInfo> {
        return response.map { item ->
            with(item) {
                PairInfo(
                    pair = pair.orEmpty(),
                    pairNormalized = pairNormalized.orEmpty(),
                    volume = volume ?: 0.0,
                    dailyPercent = dailyPercent ?: 0.0,
                    last = last ?: 0.0,
                    numeratorSymbol = numeratorSymbol.orEmpty(),
                    isFavorite = false,
                )
            }
        }
    }
}