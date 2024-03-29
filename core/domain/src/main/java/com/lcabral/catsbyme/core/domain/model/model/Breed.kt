package com.lcabral.catsbyme.core.domain.model.model

data class Breed(
    val adaptability: Int,
    val affectionLevel: Int,
    val cfaUrl: String,
    val childFriendly: Int,
    val description: String,
    val dogFriendly: Int,
    val energyLevel: Int,
    val healthIssues: Int,
    val id: String,
    val image: String,
    val intelligence: Int,
    val lifeSpan: String,
    val name: String,
    val origin: String,
    val socialNeeds: Int,
    val temperament: String,
    val vocalisation: Int,
    val referenceImageId: String
)

data class Image(
    val height: Long,
    val id: String,
    val url: String,
    val width: Long,
)
