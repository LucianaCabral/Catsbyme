package com.lcabral.catsbyme.features.images.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.catsbyme.features.images.domain.model.Photo
import com.lcabral.catsbyme.features.images.domain.useccase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PhotoViewModel @Inject constructor(
    private val getPhotoUseCase: PhotoUseCase,
) : ViewModel() {

    private val _viewState: MutableLiveData<StateView> = MutableLiveData<StateView>()
    val stateView: LiveData<StateView> = _viewState

    private val _viewAction: MutableLiveData<ActionView> = MutableLiveData<ActionView>()
    val actionView: LiveData<ActionView> = _viewAction

   fun getPhotosImages() {
        viewModelScope.launch {
            getPhotoUseCase()
                .flowOn(Dispatchers.IO)
                .onStart { }
                .catch { }
                .collect(::handleFactsSuccess)
        }
    }

    private fun handleFactsSuccess(photos: List<Photo>) {
        _viewState.value = StateView().copy(getPhotos = photos)
        println("<L> photo viewModel = $photos")
    }

    fun onNextClicked(photo: Photo) {
        _viewAction.value = ActionView.NextFact(photo)
    }
}
