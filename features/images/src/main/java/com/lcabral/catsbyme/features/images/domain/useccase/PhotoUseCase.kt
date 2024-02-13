package com.lcabral.catsbyme.features.images.domain.useccase

import com.lcabral.catsbyme.features.images.domain.model.Photo
import com.lcabral.catsbyme.features.images.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

internal class PhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(): Flow<List<Photo>> {
        return photoRepository.getPhotos()
    }
}

