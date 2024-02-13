package com.lcabral.catsbyme.features.images.domain.repository

import com.lcabral.catsbyme.features.images.domain.model.Photo
import kotlinx.coroutines.flow.Flow

internal interface PhotoRepository {
   fun getPhotos(): Flow<List<Photo>>
}
