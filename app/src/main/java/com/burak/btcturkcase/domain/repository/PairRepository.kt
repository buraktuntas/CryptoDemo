package com.burak.btcturkcase.domain.repository

import com.burak.btcturkcase.data.remote.dto.pairChart.ChartDataPointDto
import com.burak.btcturkcase.data.remote.dto.pairList.Data
import kotlinx.coroutines.flow.Flow


interface PairRepository {
    suspend fun getPairList(symbol: String): Flow<List<Data>>
    suspend fun getHistoricalData(
        symbol: String,
        resolution: String,
        from: Long,
        to: Long
    ): Flow<ChartDataPointDto>
}