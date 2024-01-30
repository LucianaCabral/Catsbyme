package com.lcabral.catsbyme.features.breeds.presentation.viewmodel

import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed

internal object StubBreeds {
    fun breed(): Breed {
        return Breed(
                adaptability = 1,
                affectionLevel = 1,
                cfaUrl = "loremipsum.jpg",
                childFriendly = 1,
                description = "The Balik cat s one of the first distinctly " +
                        "recognised breeds of Asian cat. Derived from the Wichianmat landrace," +
                        " one of several varieties of cats native to Thailand (formerly known as Siam)," +
                        "the original Siamese " +
                        "became one of the most popular breeds in Europe and North America " +
                        "in the 19th century.",
                dogFriendly = 1,
                energyLevel = 1,
                healthIssues = 1,
                id = "adsfs",
                image = "cat.jpg",
                intelligence = 1,
                lifeSpan = "10-12 years",
                name = "Siamese",
                origin = "Thailand",
                socialNeeds = 1,
                temperament = "friendly",
                vocalisation = 1,
                referenceImageId = "jpg"
            )
    }

    private fun getBreeds(): List<Breed> {
        return listOf(
            Breed(
                adaptability = 1,
                affectionLevel = 1,
                cfaUrl = "loremipsum.jpg",
                childFriendly = 1,
                description = "The Balik cat s one of the first distinctly " +
                        "recognised breeds of Asian cat. Derived from the Wichianmat landrace," +
                        " one of several varieties of cats native to Thailand (formerly known as Siam)," +
                        "the original Siamese " +
                        "became one of the most popular breeds in Europe and North America " +
                        "in the 19th century.",
                dogFriendly = 1,
                energyLevel = 1,
                healthIssues = 1,
                id = "adsfs",
                image = "cat.jpg",
                intelligence = 1,
                lifeSpan = "10-12 years",
                name = "Siamese",
                origin = "Thailand",
                socialNeeds = 1,
                temperament = "friendly",
                vocalisation = 1,
                referenceImageId = "jpg"
            ),
            Breed(
                adaptability = 1,
                affectionLevel = 1,
                cfaUrl = "loremipsum.jpg",
                childFriendly = 1,
                description = "The Siamese cat s one of the first distinctly " +
                        "recognised breeds of Asian cat. Derived from the Wichianmat landrace," +
                        " one of several varieties of cats native to Thailand (formerly known as Siam)," +
                        "the original Siamese " +
                        "became one of the most popular breeds in Europe and North America " +
                        "in the 19th century.",
                dogFriendly = 1,
                energyLevel = 1,
                healthIssues = 1,
                id = "WTETET",
                image = "cat.jpg",
                intelligence = 1,
                lifeSpan = "10-12 years",
                name = "Balik",
                origin = "Thailand",
                socialNeeds = 1,
                temperament = "friendly",
                vocalisation = 1,
                referenceImageId = "jpg"
            )
        )
    }

    val pagingData = PagingData.from(getBreeds())
}