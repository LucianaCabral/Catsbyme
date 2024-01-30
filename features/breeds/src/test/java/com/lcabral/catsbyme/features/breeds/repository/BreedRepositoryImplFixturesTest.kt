package com.lcabral.catsbyme.features.breeds.repository

import androidx.annotation.VisibleForTesting
import com.lcabral.catsbyme.core.data.remote.model.BreedResponse
import com.lcabral.catsbyme.libs.arch.testextensions.RemoteTestRule
import com.lcabral.catsbyme.libs.arch.testextensions.readFromJSONToModel
import com.lcabral.catsbyme.libs.arch.testextensions.readFromJSONToString
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

internal const val BREED_SUCCESS_RESPONSE = "breed_success.json"
internal const val BREED_FAILURE_RESPONSE = "breed_failure.json"

@VisibleForTesting
internal fun RemoteTestRule.toServerSuccessResponse(fileBlock: () -> String) {
    val body = readFromJSONToString(fileBlock())
    mockWebServerResponse(body, HttpURLConnection.HTTP_OK)
}

@VisibleForTesting
internal fun RemoteTestRule.toServerFailureResponse(fileBlock: () -> String) {
    val body = readFromJSONToString(fileBlock())
    mockWebServerResponse(body, HttpsURLConnection.HTTP_BAD_REQUEST)
}

@VisibleForTesting
internal object ResponseFactory {
    private val breedResponse = readFromJSONToModel<BreedResponse>(BREED_SUCCESS_RESPONSE)
    val breeds = breedResponse
}
