package com.lcabral.catsbyme.features.breeds.presentation.viewmodel

import com.lcabral.catsbyme.core.domain.model.model.Breed

internal sealed class ActionView {
    data class GoToDetails(val breedItem: Breed) : ActionView()
}
