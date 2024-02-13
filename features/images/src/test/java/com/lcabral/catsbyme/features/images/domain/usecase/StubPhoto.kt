package com.lcabral.catsbyme.features.images.domain.usecase

import com.lcabral.catsbyme.features.images.domain.model.Photo

internal object StubPhoto {
    fun getPhotos(): List<Photo> {
        return listOf(
            Photo(
                url="https://cdn2.thecatapi.com/images/ebv.jpg",
            )
        )
    }
}
