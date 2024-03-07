package com.burak.btcturkcase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.burak.btcturkcase.data.local.entity.PairInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface PairInfoDao {

    @Update
    fun updatePair(pairInfo: PairInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllPair(pairInfo: List<PairInfo>)

    @Query("SELECT * FROM pair_info")
    fun readAllPairData(): Flow<List<PairInfo>>

    @Query("SELECT * FROM pair_info WHERE isfavorite = 1")
    fun readAllFavorite(): Flow<List<PairInfo>>

    @Query("DELETE FROM pair_info")
    suspend fun deleteAllPair()
}