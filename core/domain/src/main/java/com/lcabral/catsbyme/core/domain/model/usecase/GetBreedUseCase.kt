package com.lcabral.catsbyme.core.domain.model.usecase

import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.core.domain.model.repository.BreedRepository
import kotlinx.coroutines.flow.Flow

class GetBreedUseCase (private val breedRepository: BreedRepository) {
    operator fun invoke(): Flow<PagingData<Breed>> {
        return breedRepository.getBreeds()
    }
}
