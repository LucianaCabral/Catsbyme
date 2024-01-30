package com.lcabral.catsbyme.features.breeds.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.lcabral.catsbyme.core.domain.model.usecase.GetBreedUseCase
import com.lcabral.catsbyme.features.breeds.presentation.viewmodel.StubBreeds.pagingData
import com.lcabral.catsbyme.features.breeds.utils.MainDispatcherMainTestRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class BreedViewModelTest {
    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherMainTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getBreedsUseCase: GetBreedUseCase = mockk()

    private lateinit var subject: BreedViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun instantiateViewModel() {
        subject = BreedViewModel(
            getBreedsUseCase
        )
    }

    @Test
    fun `getBreeds Should show breeds When is invoked`() = runTest {
        // Given
        every { getBreedsUseCase() } returns flowOf(pagingData)

        // When
        instantiateViewModel()

        // Then
        subject.state.test {
            assertNotNull(awaitItem().breeds)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `onAdapterItemClicked Should emit expected action When clicked is clicked`() = runTest {
        // Given
        val expectedAction = ActionView.ItemClicked(StubBreeds.breed())
        every { getBreedsUseCase() } returns mockk(relaxed = true)
        instantiateViewModel()

        // When
        subject.onAdapterItemClicked(StubBreeds.breed())

        //Then
        subject.action.test {
            assertEquals(expectedAction, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        coVerify { getBreedsUseCase() }
    }


}

