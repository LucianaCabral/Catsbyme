package com.lcabral.catsbyme.features.breeds.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcabral.catsbyme.core.data.remote.model.toBreeds
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.features.breeds.data.source.BreedDataSource

internal class BreedPagingDataSource(
    private val remoteDataSource: BreedDataSource
) : PagingSource<Int, Breed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return runCatching {
            val limit = params.loadSize
            val page = params.key ?: 1
            val response = remoteDataSource.getBreeds(limit, page).toBreeds()

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.dec(),
                nextKey = if (response.isEmpty()) null else page.inc()
            )

        }.getOrElse {
            LoadResult.Error(throwable = it)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
