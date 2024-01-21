package com.lcabral.catsbyme.features.breeds.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.core.domain.model.repository.BreedRepository
import com.lcabral.catsbyme.features.breeds.data.source.BreedDataSource
import kotlinx.coroutines.flow.Flow

internal class BreedRepositoryImpl(
    private val remoteDataSource: BreedDataSource,
    private val config: PagingConfig
) : BreedRepository {
    override fun getBreeds(): Flow<PagingData<Breed>> {
        return Pager(
            config = config,
            pagingSourceFactory = remoteDataSource::getBreedPagingSource
        ).flow
    }
}
