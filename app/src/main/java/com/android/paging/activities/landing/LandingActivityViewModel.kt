package com.android.paging.activities.landing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.android.paging.networking.PagingRepository
import com.android.paging.pojo.Posts
import com.android.paging.activities.landing.posts.PostsPagingSource
import kotlinx.coroutines.flow.Flow

class LandingActivityViewModel(application: Application, private val repository: PagingRepository) : AndroidViewModel(application) {

    val getPosts: Flow<PagingData<Posts>> = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 6),
            pagingSourceFactory = { PostsPagingSource(repository) }
    ).flow.cachedIn(viewModelScope)

}