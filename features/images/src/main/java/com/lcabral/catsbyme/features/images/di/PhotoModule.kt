package com.lcabral.catsbyme.features.images.di

import com.lcabral.catsbyme.core.data.remote.network.HttpClient
import com.lcabral.catsbyme.features.images.data.api.PhotoService
import com.lcabral.catsbyme.features.images.data.repository.PhotoRepositoryImpl
import com.lcabral.catsbyme.features.images.data.source.PhotoDataSource
import com.lcabral.catsbyme.features.images.data.source.PhotoDataSourceImpl
import com.lcabral.catsbyme.features.images.domain.repository.PhotoRepository
import com.lcabral.catsbyme.features.images.domain.useccase.PhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PhotoModule {

    @Provides
    @Singleton
    fun provideService(
        httpClient: HttpClient
    ): PhotoService {
        return httpClient.create(PhotoService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(
        service: PhotoService
    ): PhotoDataSource {
        return PhotoDataSourceImpl(service = service)
    }

    @Provides
    fun provideFactsRepository(
        photoDataSource: PhotoDataSource
    ): PhotoRepository {
        return PhotoRepositoryImpl(photoDataSource = photoDataSource)
    }

    @Provides
    fun provideGetGetFactsUseCase(
        repository: PhotoRepository
    ): PhotoUseCase {
        return PhotoUseCase(repository)
    }
}