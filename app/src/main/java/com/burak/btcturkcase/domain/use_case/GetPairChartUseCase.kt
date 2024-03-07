package com.burak.btcturkcase.domain.use_case

import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.data.local.PairDataRepository
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.data.remote.dto.pairChart.ChartDataPointDto
import com.burak.btcturkcase.data.remote.dto.pairList.Data
import com.burak.btcturkcase.domain.model.ChartData
import com.burak.btcturkcase.domain.repository.PairRepository
import kotlinx.coroutines.flow.catch

class GetPairChartUseCase(
    private val pairRepository: PairRepository,
) {
    suspend operator fun invoke(
        symbol: String,
        resolution: String,
        from: Long,
        to: Long,
        onResult: suspend (ResultState<List<ChartData>>) -> Unit
    ) {
        pairRepository.getHistoricalData(symbol, resolution, from, to)
            .catch { exception ->
                onResult(ResultState.Error(exception))
            }
            .collect { chartDtoList ->
                val chartData = convertResponseToUI(chartDtoList)
                onResult(ResultState.Success(chartData))
            }
    }

    private fun convertResponseToUI(dto: ChartDataPointDto): List<ChartData> {
        val timeList = dto.t?.filterNotNull() ?: listOf()
        val closeList = dto.c?.filterNotNull() ?: listOf()

        return timeList.zip(closeList) { time, close ->
            ChartData(time, close)
        }
    }
}