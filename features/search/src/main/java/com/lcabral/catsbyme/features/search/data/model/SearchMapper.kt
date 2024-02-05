package com.lcabral.catsbyme.features.search.data.model

import com.lcabral.catsbyme.core.data.remote.model.BreedResponse
import com.lcabral.catsbyme.features.search.domain.model.Search
import com.lcabral.catsbyme.libs.arch.extensions.orEmpty

internal fun List<BreedResponse>.toSearchBreeds() =
    this.map {
        it.toSearchBreed()
    }

private fun BreedResponse?.toSearchBreed(): Search {
    return Search(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        referenceImageId = this?.referenceImageId.orEmpty(),
    )
}
