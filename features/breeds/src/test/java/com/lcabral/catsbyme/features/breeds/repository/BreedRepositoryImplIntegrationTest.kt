package com.lcabral.catsbyme.features.breeds.repository

import androidx.paging.PagingConfig
import app.cash.turbine.test
import com.lcabral.catsbyme.core.domain.model.repository.BreedRepository
import com.lcabral.catsbyme.features.breeds.data.repository.BreedRepositoryImpl
import com.lcabral.catsbyme.features.breeds.data.source.BreedDataSourceImpl
import com.lcabral.catsbyme.libs.arch.testextensions.MainDispatchersTestRule
import com.lcabral.catsbyme.libs.arch.testextensions.RemoteTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class BreedRepositoryImplIntegrationTest {
    @get:Rule
    val remoteRule = RemoteTestRule()


    @get:Rule
    val mainCoroutineRule = MainDispatchersTestRule()

    private lateinit var subject: BreedRepository

    @Before
    fun setUp() {
        subject =  BreedRepositoryImpl(
            remoteDataSource = BreedDataSourceImpl(
                service = remoteRule.createTestService()
            ),
            config = PagingConfig(pageSize = 10)
        )
    }

    @Test
    fun `getBreeds Should validate flow data creation When invoked`() = runTest {
        // Given
        remoteRule.toServerSuccessResponse { BREED_SUCCESS_RESPONSE }

        // When
        val result = subject.getBreeds()

        // Then
        result.test {
            assertNotNull(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}