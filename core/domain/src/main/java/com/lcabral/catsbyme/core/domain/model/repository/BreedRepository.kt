package com.lcabral.catsbyme.core.domain.model.repository

import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedRepository {
    fun getBreeds(): Flow<PagingData<Breed>>
}
