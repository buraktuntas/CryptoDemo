package com.burak.btcturkcase.data.remote.dto.pairList


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ask")
    val ask: Double? = 0.0,
    @SerializedName("average")
    val average: Double? = 0.0,
    @SerializedName("bid")
    val bid: Double? = 0.0,
    @SerializedName("daily")
    val daily: Double? = 0.0,
    @SerializedName("dailyPercent")
    val dailyPercent: Double? = 0.0,
    @SerializedName("denominatorSymbol")
    val denominatorSymbol: String? = "",
    @SerializedName("high")
    val high: Double? = 0.0,
    @SerializedName("last")
    val last: Double? = 0.0,
    @SerializedName("low")
    val low: Double? = 0.0,
    @SerializedName("numeratorSymbol")
    val numeratorSymbol: String? = "",
    @SerializedName("open")
    val `open`: Double? = 0.0,
    @SerializedName("order")
    val order: Int? = 0,
    @SerializedName("pair")
    val pair: String? = "",
    @SerializedName("pairNormalized")
    val pairNormalized: String? = "",
    @SerializedName("timestamp")
    val timestamp: Long? = 0,
    @SerializedName("volume")
    val volume: Double? = 0.0
)