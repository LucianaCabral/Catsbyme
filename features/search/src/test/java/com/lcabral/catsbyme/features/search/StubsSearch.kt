package com.lcabral.catsbyme.features.search

import com.lcabral.catsbyme.features.search.domain.model.Search

internal object StubsSearch {
    fun searchBreeds(): List<Search> {
        return listOf(
            Search(
                id = "adsfs",
                name = "Siamese",
                referenceImageId = "jpg"
            )
        )
    }
}