package com.lcabral.catsbyme.core.data.remote.di

import androidx.paging.PagingConfig
import com.lcabral.catsbyme.core.data.remote.BuildConfig
import com.lcabral.catsbyme.core.data.remote.network.QueryInterceptor
import com.lcabral.catsbyme.core.data.remote.network.HttpClient
import com.lcabral.catsbyme.core.data.remote.network.HttpClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    private const val READ_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 15L

    @Provides
    fun provideQueryInterceptor(): QueryInterceptor {
        return QueryInterceptor()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE

        return HttpLoggingInterceptor().apply {
            setLevel(level)
        }
    }

    @Provides
    fun provideOkHttpClient(
        queryInterceptor: QueryInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(queryInterceptor)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    fun provideHttpClient(
        retrofit: Retrofit
    ): HttpClient {
        return HttpClientImpl(retrofit)
    }

    @Provides
    fun providesPagingConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, initialLoadSize = 5)
    }
}
