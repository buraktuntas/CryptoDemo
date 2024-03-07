package com.burak.btcturkcase.data.remote.dto.pairList


import com.google.gson.annotations.SerializedName

data class PairListDto(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("data")
    val `data`: List<Data?>? = listOf(),
    @SerializedName("message")
    val message: Any? = Any(),
    @SerializedName("success")
    val success: Boolean? = false
)