package com.lcabral.catsbyme.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.catsbyme.features.search.domain.model.Search
import com.lcabral.catsbyme.features.search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _stateView: MutableLiveData<StateView> = MutableLiveData<StateView>()
    val stateView: LiveData<StateView> = _stateView

    private val _viewAction: MutableLiveData<ActionView> = MutableLiveData<ActionView>()
    val viewAction: LiveData<ActionView> = _viewAction

    fun submittedSearch(query: String) {
        viewModelScope.launch {
            searchUseCase(query)
                .flowOn(Dispatchers.IO)
                .onStart {}
                .catch { onHandleError() }
                .collect(::onHandleSuccess)
        }
        _viewAction.value = ActionView.HideKeyboard
    }

    private fun onHandleError() {
        _stateView.value = StateView().handleErrorState()
        _viewAction.value = ActionView.ShowError
    }

    private fun onHandleSuccess(searchBreeds: List<Search>) {
        if (searchBreeds.isNotEmpty()) {
            _stateView.value = StateView().handleSuccessState(searchBreeds)
        }
    }
}

