package com.lcabral.catsbyme.features.search.domain.usecase

import com.lcabral.catsbyme.features.search.domain.model.Search
import com.lcabral.catsbyme.features.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

internal class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(query:String): Flow<List<Search>> {
        return searchRepository.searchImages(query)
    }
}