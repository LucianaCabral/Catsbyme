package com.lcabral.catsbyme.features.breeds.data.source

import com.lcabral.catsbyme.core.data.remote.model.BreedResponse
import com.lcabral.catsbyme.features.breeds.data.paging.BreedPagingDataSource

internal interface BreedDataSource {
    suspend fun getBreeds(limit: Int, page: Int): List<BreedResponse>
    fun getBreedPagingSource(): BreedPagingDataSource
}
