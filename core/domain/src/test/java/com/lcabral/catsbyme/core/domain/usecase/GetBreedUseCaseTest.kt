package com.lcabral.catsbyme.core.domain.usecase

import app.cash.turbine.test
import com.lcabral.catsbyme.core.domain.model.repository.BreedRepository
import com.lcabral.catsbyme.core.domain.model.usecase.GetBreedUseCase
import com.lcabral.catsbyme.core.domain.usecase.StubBreeds.pagingData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
internal class GetBreedUseCaseTest {
    private val repository: BreedRepository = mockk(relaxed = true)
    private val subject = GetBreedUseCase(repository)

    @Test
    fun `getBreeds Should return breeds When invoked`() = runBlocking {
        // Given
        val result = pagingData

        every { repository.getBreeds() } returns flow { emit(result) }

        // When
        val breedResult = subject.invoke()

        // Then
        breedResult.test {
            assertEquals(result, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getBreeds() }

    }

    @Test
    fun `getBreeds Should return exception when invoked breeds`() = runTest {
        // Given
        val cause = Throwable()
        every { repository.getBreeds() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(cause, awaitError())
        }
        verify { repository.getBreeds() }
    }
}
