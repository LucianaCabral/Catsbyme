package com.lcabral.catsbyme.features.search.domain.repository

import com.lcabral.catsbyme.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

internal interface SearchRepository {
    fun searchImages(query:String): Flow<List<Search>>
}
