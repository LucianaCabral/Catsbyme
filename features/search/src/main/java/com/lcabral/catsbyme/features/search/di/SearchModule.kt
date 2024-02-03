package com.lcabral.catsbyme.features.search.di

import com.lcabral.catsbyme.core.data.remote.network.HttpClient
import com.lcabral.catsbyme.features.search.data.api.SearchService
import com.lcabral.catsbyme.features.search.data.repository.SearchRepositoryImpl
import com.lcabral.catsbyme.features.search.data.source.SearchDataSource
import com.lcabral.catsbyme.features.search.data.source.SearchDataSourceImpl
import com.lcabral.catsbyme.features.search.domain.repository.SearchRepository
import com.lcabral.catsbyme.features.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SearchModule {

    @Provides
    @Singleton
    fun provideService(
        httpClient: HttpClient
    ): SearchService {
        return httpClient.create(SearchService::class.java)
    }

    @Provides
    fun provideSearchRemoteDataSource(service: SearchService

    ): SearchDataSource {
        return SearchDataSourceImpl(service = service)
    }

    @Provides
    fun provideSearchRepository(
        remoteDataSource: SearchDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(searchDataSource = remoteDataSource)
    }

    @Provides
    fun provideGetGetBreedUseCase(
        repository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(repository)
    }
}