package com.burak.btcturkcase.di

import android.content.Context
import androidx.room.Room
import com.burak.btcturkcase.data.local.PairDatabase
import com.burak.btcturkcase.data.local.PairInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providePairDao(pairDatabase: PairDatabase): PairInfoDao {
        return pairDatabase.pairDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): PairDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            PairDatabase::class.java,
            "pair_database"
        ).build()
    }
}