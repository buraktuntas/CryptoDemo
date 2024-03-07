package com.burak.btcturkcase.di

import com.burak.btcturkcase.data.local.PairDataRepository
import com.burak.btcturkcase.domain.repository.PairRepository
import com.burak.btcturkcase.domain.use_case.GetPairChartUseCase
import com.burak.btcturkcase.domain.use_case.GetPairListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    /* Use Case */
    @ViewModelScoped
    @Provides
    fun provideCallGetPairListUseCase(
        pairRepository: PairRepository,
        pairDataRepository: PairDataRepository
    ): GetPairListUseCase {
        return GetPairListUseCase(
            pairRepository,
            pairDataRepository
        )
    }

    @ViewModelScoped
    @Provides
    fun provideCallGetPairChartUseCase(
        pairRepository: PairRepository,
    ): GetPairChartUseCase {
        return GetPairChartUseCase(
            pairRepository,
        )
    }
}