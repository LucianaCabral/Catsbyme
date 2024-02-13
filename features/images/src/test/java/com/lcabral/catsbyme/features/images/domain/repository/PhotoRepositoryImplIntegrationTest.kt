package com.lcabral.catsbyme.features.images.domain.repository

import app.cash.turbine.test
import com.lcabral.catsbyme.features.images.data.repository.PhotoRepositoryImpl
import com.lcabral.catsbyme.features.images.data.source.PhotoDataSourceImpl
import com.lcabral.catsbyme.libs.arch.testextensions.MainDispatchersTestRule
import com.lcabral.catsbyme.libs.arch.testextensions.RemoteTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
internal class PhotoRepositoryImplIntegrationTest {

    @get:Rule
    val remoteRule = RemoteTestRule()

    @get:Rule
    val mainCoroutineRule = MainDispatchersTestRule()

    private lateinit var subject: PhotoRepository

    @Before
    fun setUp() {
        subject = PhotoRepositoryImpl(
            photoDataSource = PhotoDataSourceImpl(
                service = remoteRule.createTestService()
            )
        )
    }

    @Test
    fun `getBreeds Should validate flow data creation When invoked`() = runTest {
        // Given
        remoteRule.toServerSuccessResponse { PHOTO_SUCCESS_RESPONSE }

        // When
        val result = subject.getPhotos()

        // Then
        result.test {
            assertNotNull(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
