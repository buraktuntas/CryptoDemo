package com.burak.btcturkcase.data.remote

import com.burak.btcturkcase.data.remote.dto.pairChart.ChartDataPointDto
import com.burak.btcturkcase.data.remote.dto.pairList.PairListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface PairServices {

    @GET("api/v2/ticker/currency")
    suspend fun getPairList(@Query("symbol") symbol: String): PairListDto

    @GET("v1/klines/history")
    suspend fun getHistoricalData(
        @Query("symbol") symbol: String,
        @Query("resolution") resolution: String,
        @Query("from") from: Long,
        @Query("to") to: Long
    ): ChartDataPointDto
}