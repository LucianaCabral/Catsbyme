package com.lcabral.catsbyme.features.search

import app.cash.turbine.test
import com.lcabral.catsbyme.features.search.StubsSearch.searchBreeds
import com.lcabral.catsbyme.features.search.domain.repository.SearchRepository
import com.lcabral.catsbyme.features.search.domain.usecase.SearchUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
internal class SearchUseCaseTest {

        private val repository: SearchRepository = mockk(relaxed = true)
        private val subject = SearchUseCase(repository)

        @Test
        fun `getBreeds Should return breeds When invoked`() = runBlocking {
            // Given
            val id = "adsfs"
            val result = searchBreeds()

            every { repository.searchImages(query = id) } returns flow { emit(result) }

            // When
            val breedResult = subject.invoke(id)

            // Then
            breedResult.test {
                assertEquals(result, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
            verify { repository.searchImages(id) }

        }

        @Test
        fun `getBreeds Should return exception when invoked breeds`() = runTest {
            // Given
            val id = ""
            val cause = Throwable()
            every { repository.searchImages(query =  "") } returns flow { throw cause }

            // When
            val result = subject(id)

            // Then
            result.test {
                assertEquals(cause, awaitError())
            }
            verify { repository.searchImages(id) }
        }
    }