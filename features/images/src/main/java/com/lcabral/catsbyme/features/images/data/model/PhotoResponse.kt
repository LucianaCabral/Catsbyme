package com.lcabral.catsbyme.features.images.data.model

import com.google.gson.annotations.SerializedName

internal data class PhotoResponse(
    @SerializedName("url")
    val url: String? = ""
)
