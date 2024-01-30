package com.lcabral.catsbyme.features.breeds.di

import androidx.paging.PagingConfig
import com.lcabral.catsbyme.core.data.remote.network.HttpClient
import com.lcabral.catsbyme.core.domain.model.repository.BreedRepository
import com.lcabral.catsbyme.core.domain.model.usecase.GetBreedUseCase
import com.lcabral.catsbyme.features.breeds.data.repository.BreedRepositoryImpl
import com.lcabral.catsbyme.features.breeds.data.service.BreedService
import com.lcabral.catsbyme.features.breeds.data.source.BreedDataSource
import com.lcabral.catsbyme.features.breeds.data.source.BreedDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BreedModule {

    @Provides
    @Singleton
    fun provideService(
        httpClient: HttpClient
    ): BreedService {
        return httpClient.create(BreedService::class.java)
    }

    @Provides
    fun provideBreedRemoteDataSource(
        service: BreedService
    ): BreedDataSource {
        return BreedDataSourceImpl(service = service)
    }

    @Provides
    fun provideBreedRepository(
        remoteDataSource: BreedDataSource,
        config: PagingConfig
    ): BreedRepository {
        return BreedRepositoryImpl(remoteDataSource, config)
    }

    @Provides
    fun provideGetGetBreedUseCase(
        repository: BreedRepository
    ): GetBreedUseCase {
        return GetBreedUseCase(repository)
    }
}