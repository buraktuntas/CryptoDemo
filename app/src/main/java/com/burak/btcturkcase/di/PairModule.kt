package com.burak.btcturkcase.di

import com.burak.btcturkcase.data.remote.PairServices
import com.burak.btcturkcase.data.repository.PairRepositoryImpl
import com.burak.btcturkcase.domain.repository.PairRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PairModule {

    /* Services */
    @Provides
    @Singleton
    fun providePairService(
        retrofit: Retrofit
    ): PairServices {
        return retrofit.create(PairServices::class.java)
    }

    /* Repositories */
    @Provides
    @Singleton
    fun providePairListRepository(
        service: PairServices,
    ): PairRepository = PairRepositoryImpl(service)

}