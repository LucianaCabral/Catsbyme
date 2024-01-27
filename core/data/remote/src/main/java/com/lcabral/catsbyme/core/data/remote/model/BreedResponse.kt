package com.lcabral.catsbyme.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    val breeds: List<ResultResponse?>?
) {
    data class ResultResponse(
        @SerializedName("adaptability")
        val adaptability: Int?,
        @SerializedName("affection_level")
        val affectionLevel: Int?,
        @SerializedName("cfa_url")
        val cfaUrl: String?,
        @SerializedName("child_friendly")
        val childFriendly: Int?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("dog_friendly")
        val dogFriendly: Int?,
        @SerializedName("energy_level")
        val energyLevel: Int?,
        @SerializedName("health_issues")
        val healthIssues: Int?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("image")
        val image: Image?,
        @SerializedName("intelligence")
        val intelligence: Int?,
        @SerializedName("life_span")
        val lifeSpan: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("origin")
        val origin: String?,
        @SerializedName("social_needs")
        val socialNeeds: Int?,
        @SerializedName("temperament")
        val temperament: String?,
        @SerializedName("vocalisation")
        val vocalisation: Int?,
    )
}

