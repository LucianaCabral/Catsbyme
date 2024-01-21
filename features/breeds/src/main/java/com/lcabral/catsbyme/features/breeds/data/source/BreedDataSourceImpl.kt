package com.lcabral.catsbyme.features.breeds.data.source

import com.lcabral.catsbyme.core.data.remote.model.BreedResponse
import com.lcabral.catsbyme.features.breeds.data.paging.BreedPagingDataSource
import com.lcabral.catsbyme.features.breeds.data.service.BreedService

internal class BreedDataSourceImpl(
    private val service: BreedService,

    ) : BreedDataSource {
    override suspend fun getBreeds(limit: Int, page: Int): BreedResponse =
        service.getBreeds(limit, page)

    override fun getBreedPagingSource(): BreedPagingDataSource {
        return BreedPagingDataSource(remoteDataSource = this)
    }
}
