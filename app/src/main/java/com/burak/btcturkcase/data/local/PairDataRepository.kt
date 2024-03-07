package com.burak.btcturkcase.data.local

import com.burak.btcturkcase.data.local.entity.PairInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PairDataRepository @Inject constructor(private val pairInfoDao: PairInfoDao) {

    fun allList(): Flow<List<PairInfo>> {
        return pairInfoDao.readAllPairData()
    }

    suspend fun addAll(pairInfo: List<PairInfo>) {
        pairInfoDao.addAllPair(pairInfo)
    }

    fun readAllFavorite(): Flow<List<PairInfo>> {
        return pairInfoDao.readAllFavorite()
    }

    fun updatePair(pairInfo: PairInfo) {
        pairInfoDao.updatePair(pairInfo)
    }

    suspend fun deleteAllPair() {
        pairInfoDao.deleteAllPair()
    }
}