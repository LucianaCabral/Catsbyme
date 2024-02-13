package com.lcabral.catsbyme.features.images.data.source

import com.lcabral.catsbyme.features.images.domain.model.Photo
import kotlinx.coroutines.flow.Flow

internal interface PhotoDataSource {
    fun getPhotos(): Flow<List<Photo>>
}
