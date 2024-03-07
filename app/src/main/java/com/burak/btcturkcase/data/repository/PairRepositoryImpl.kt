package com.burak.btcturkcase.data.repository

import com.burak.btcturkcase.data.remote.PairServices
import com.burak.btcturkcase.data.remote.dto.pairChart.ChartDataPointDto
import com.burak.btcturkcase.data.remote.dto.pairList.Data
import com.burak.btcturkcase.domain.repository.PairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PairRepositoryImpl @Inject constructor(private val services: PairServices) :
    PairRepository {

    override suspend fun getPairList(symbol: String): Flow<List<Data>> = flow {
        while (true) {
            val response = services.getPairList(symbol)
            response.data?.let {
                emit(it.filterNotNull())
            }
            delay(5000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getHistoricalData(
        symbol: String,
        resolution: String,
        from: Long,
        to: Long
    ): Flow<ChartDataPointDto> = flow {
        val response = services.getHistoricalData(symbol, resolution, from, to)
        emit(response)
    }.flowOn(Dispatchers.IO)
}
