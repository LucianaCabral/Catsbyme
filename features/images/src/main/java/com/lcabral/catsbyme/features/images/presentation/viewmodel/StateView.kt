package com.lcabral.catsbyme.features.images.presentation.viewmodel

import com.lcabral.catsbyme.features.images.domain.model.Photo

internal data class StateView(
    val getPhotos: List<Photo> = emptyList(),
)
