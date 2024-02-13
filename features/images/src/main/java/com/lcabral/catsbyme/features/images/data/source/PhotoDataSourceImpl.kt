package com.lcabral.catsbyme.features.images.data.source

import com.lcabral.catsbyme.features.images.data.api.PhotoService
import com.lcabral.catsbyme.features.images.data.model.toPhotos
import com.lcabral.catsbyme.features.images.domain.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PhotoDataSourceImpl(
    private val service: PhotoService
) : PhotoDataSource {
    override  fun getPhotos(): Flow<List<Photo>> {
        return flow {
            emit(service.getPhotos().toPhotos()).apply {
            }
        }
    }
}
