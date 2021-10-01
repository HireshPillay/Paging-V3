package com.android.paging.activities.landing.posts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.paging.networking.PagingRepository
import com.android.paging.pojo.Posts

class PostsPagingSource(private val backend: PagingRepository) : PagingSource<Int, Posts>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Posts> {
        return try {
            val currentPage = params.key ?: 1
            val response = backend.getPosts(currentPage)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (response.data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Posts>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
