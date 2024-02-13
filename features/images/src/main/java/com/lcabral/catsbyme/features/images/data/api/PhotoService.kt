package com.lcabral.catsbyme.features.images.data.api

import com.lcabral.catsbyme.features.images.data.model.PhotoResponse
import retrofit2.http.GET

internal interface PhotoService {

    @GET("v1/images/search?order=random&limit=50")
    suspend fun getPhotos(): List<PhotoResponse>
}
