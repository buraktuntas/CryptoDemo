package com.burak.btcturkcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.util.StringListConverter


@Database(entities = [PairInfo::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class PairDatabase : RoomDatabase() {

    abstract fun pairDao(): PairInfoDao

}