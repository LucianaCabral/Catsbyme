package com.lcabral.catsbyme.core.data.remote.model

import com.lcabral.catsbyme.core.data.remote.model.BreedResponse.ResultResponse
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.libs.arch.extensions.orZero

fun List<ResultResponse?>?.toBreeds(): List<Breed> {
    return this?.map {
        it.toBreed()
    }.orEmpty()
}

private fun ResultResponse?.toBreed(): Breed {
    return Breed(
        adaptability = this?.adaptability.orZero(),
        affectionLevel = this?.affectionLevel.orZero(),
        cfaUrl = this?.cfaUrl.orEmpty(),
        childFriendly = this?.childFriendly.orZero(),
        description = this?.description.orEmpty(),
        dogFriendly = this?.dogFriendly.orZero(),
        healthIssues = this?.healthIssues.orZero(),
        id = this?.id.orEmpty(),
        intelligence = this?.intelligence.orZero(),
        lifeSpan = this?.lifeSpan.orEmpty(),
        origin = this?.origin.orEmpty(),
        socialNeeds = this?.socialNeeds.orZero(),
        vocalisation = this?.vocalisation.orZero(),
        temperament = this?.temperament.orEmpty(),
        energyLevel = this?.energyLevel.orZero(),
        name = this?.name.orEmpty(),
        image = this?.image?.url.orEmpty()
    )
}
