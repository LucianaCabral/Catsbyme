package com.lcabral.catsbyme.features.images.domain.usecase

import app.cash.turbine.test
import com.lcabral.catsbyme.features.images.domain.repository.PhotoRepository
import com.lcabral.catsbyme.features.images.domain.usecase.StubPhoto.getPhotos
import com.lcabral.catsbyme.features.images.domain.useccase.PhotoUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
internal class PhotoUseCaseTest {

    private val repository: PhotoRepository = mockk(relaxed = true)
    private val subject = PhotoUseCase(repository)

    @Test
    fun `getPhotos Should return photos When invoked`() = runBlocking {
        // Given

        val result = getPhotos()

        every { repository.getPhotos() } returns flow { emit(result) }

        // When
        val breedResult = subject.invoke()

        // Then
        breedResult.test {
            Assert.assertEquals(result, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getPhotos() }

    }

    @Test
    fun `getPhotos Should return exception when invoked photos`() = runTest {
        // Given
        val cause = Throwable()
        every { repository.getPhotos() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            Assert.assertEquals(cause, awaitError())
        }
        verify { repository.getPhotos() }
    }
}