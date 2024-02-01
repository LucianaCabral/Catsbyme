package com.lcabral.catsbyme.features.search.data.source

import com.lcabral.catsbyme.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

internal interface SearchDataSource {
    fun searchBreeds(query:String): Flow<List<Search>>
}

