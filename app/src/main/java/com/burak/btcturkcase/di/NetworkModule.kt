package com.burak.btcturkcase.di

import com.burak.btcturkcase.common.Constants
import com.burak.btcturkcase.common.Constants.API_SERVICE_TIMEOUT
import com.burak.btcturkcase.data.network.DynamicBaseUrlInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideDynamicBaseUrlInterceptor(): DynamicBaseUrlInterceptor = DynamicBaseUrlInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dynamicBaseUrlInterceptor: DynamicBaseUrlInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(API_SERVICE_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(API_SERVICE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(dynamicBaseUrlInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

}