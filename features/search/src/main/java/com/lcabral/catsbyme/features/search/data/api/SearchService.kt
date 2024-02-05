package com.lcabral.catsbyme.features.search.data.api

import com.lcabral.catsbyme.core.data.remote.model.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchService {

    @GET("v1/breeds/search")
    suspend fun searchBreeds(
        @Query("q") id: String): List<BreedResponse>
}


