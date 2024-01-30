package com.lcabral.catsbyme.features.breeds.presentation.viewmodel

import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class StateView(
    val isLoading: Boolean = true,
    val breeds: Flow<PagingData<Breed>> = emptyFlow()
)
