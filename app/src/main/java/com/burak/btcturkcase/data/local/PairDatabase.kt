package com.burak.btcturkcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.burak.btcturkcase.data.local.entity.PairInfo


@Database(entities = [PairInfo::class], version = 1, exportSchema = false)
abstract class PairDatabase : RoomDatabase() {

    abstract fun pairDao(): PairInfoDao

}