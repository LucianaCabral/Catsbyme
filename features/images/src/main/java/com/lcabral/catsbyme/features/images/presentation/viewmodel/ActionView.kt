package com.lcabral.catsbyme.features.images.presentation.viewmodel

import com.lcabral.catsbyme.features.images.domain.model.Photo

internal sealed interface ActionView  {
    data class NextFact(val photo: Photo): ActionView
}

