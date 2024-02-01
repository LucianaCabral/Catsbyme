package com.lcabral.catsbyme.features.search.data.source

import com.lcabral.catsbyme.features.search.data.api.SearchService
import com.lcabral.catsbyme.features.search.data.model.toSearchBreeds
import com.lcabral.catsbyme.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SearchDataSourceImpl(
    private val service: SearchService
) : SearchDataSource {
    override fun searchBreeds(query:String): Flow<List<Search>> {
        return flow {
            emit(service.searchBreeds(query).toSearchBreeds()).apply {
                println("<L> data source = $query")
            }
        }
    }
}

