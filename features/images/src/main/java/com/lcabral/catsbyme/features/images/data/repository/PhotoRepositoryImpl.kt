package com.lcabral.catsbyme.features.images.data.repository

import com.lcabral.catsbyme.features.images.data.source.PhotoDataSource
import com.lcabral.catsbyme.features.images.domain.model.Photo
import com.lcabral.catsbyme.features.images.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

internal class PhotoRepositoryImpl(
    private val photoDataSource: PhotoDataSource
) : PhotoRepository {
    override fun getPhotos(): Flow<List<Photo>> {
        return photoDataSource.getPhotos()
    }
}
