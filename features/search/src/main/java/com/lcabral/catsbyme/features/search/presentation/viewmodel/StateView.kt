package com.lcabral.catsbyme.features.search.presentation.viewmodel

import com.lcabral.catsbyme.features.search.domain.model.Search

internal data class StateView(
    val shouldShowLoading: Boolean = false,
    val searchBreed: List<Search> = emptyList(),
    val query: String = "",

) {
    fun handleSuccessState(searchBreeds: List<Search>) = copy(
        shouldShowLoading = false,
        searchBreed = searchBreeds
    )

    fun handleErrorState() = copy(
        shouldShowLoading = false,
        searchBreed = emptyList()
    )
}

