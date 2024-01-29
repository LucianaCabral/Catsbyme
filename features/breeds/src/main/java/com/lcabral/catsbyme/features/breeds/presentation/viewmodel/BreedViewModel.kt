package com.lcabral.catsbyme.features.breeds.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.core.domain.model.usecase.GetBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class BreedViewModel @Inject constructor(
    private val getBreedUseCase: GetBreedUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<StateView> = MutableStateFlow(StateView())
    val state: StateFlow<StateView> = _state.asStateFlow()

    private val _action: Channel<ActionView> = Channel<ActionView>(Channel.CONFLATED)
    val action: Flow<ActionView> = _action.receiveAsFlow()

    init {
        getBreeds()
    }

    private fun getBreeds() {
        val breeds = getBreedUseCase()
            .onStart { handleLoading() }
            .catch { }
            .cachedIn(viewModelScope)
        _state.value = StateView().copy(isLoading = false, breeds = breeds)
    }

    private fun handleLoading() {
        _state.value = StateView().copy(isLoading = false, breeds = emptyFlow())
    }

    fun onAdapterItemClicked(breed: Breed) {
        _action.trySend(ActionView.ItemClicked(breed))
        println("clicou viewmodel")
    }
}