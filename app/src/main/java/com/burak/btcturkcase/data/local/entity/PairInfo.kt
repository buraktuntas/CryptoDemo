package com.burak.btcturkcase.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "pair_info")
data class PairInfo(
    @PrimaryKey
    @ColumnInfo(name = "pair")
    val pair: String,
    @ColumnInfo(name = "pairNormalized")
    val pairNormalized: String? = null,
    @ColumnInfo(name = "volume")
    val volume: Double? = null,
    @ColumnInfo(name = "dailyPercent")
    val dailyPercent: Double? = null,
    @ColumnInfo(name = "last")
    val last: Double? = null,
    @ColumnInfo(name = "numeratorSymbol")
    val numeratorSymbol: String? = null,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean? = null,
) : Parcelable