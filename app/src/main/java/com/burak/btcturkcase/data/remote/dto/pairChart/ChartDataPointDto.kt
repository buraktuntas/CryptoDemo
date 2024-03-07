package com.burak.btcturkcase.data.remote.dto.pairChart


import com.google.gson.annotations.SerializedName

data class ChartDataPointDto(
    @SerializedName("c")
    val c: List<Float?>? = listOf(),
    @SerializedName("h")
    val h: List<Float?>? = listOf(),
    @SerializedName("l")
    val l: List<Float?>? = listOf(),
    @SerializedName("o")
    val o: List<Float?>? = listOf(),
    @SerializedName("s")
    val s: String? = "",
    @SerializedName("t")
    val t: List<Long?>? = listOf(),
    @SerializedName("v")
    val v: List<Double?>? = listOf()
)