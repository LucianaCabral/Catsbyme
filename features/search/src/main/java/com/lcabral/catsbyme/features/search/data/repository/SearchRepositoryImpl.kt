package com.lcabral.catsbyme.features.search.data.repository

import com.lcabral.catsbyme.features.search.data.source.SearchDataSource
import com.lcabral.catsbyme.features.search.domain.model.Search
import com.lcabral.catsbyme.features.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

internal class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override fun searchImages(query:String): Flow<List<Search>> {
        return searchDataSource.searchBreeds(query).apply {
            println("<L> repository = $query")
        }
    }
}