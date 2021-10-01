package com.android.paging.networking

import org.koin.dsl.module

val pagingRepositoryModule = module {
    factory { PagingRepository(get()) }
}

class PagingRepository(private val apis: PagingApis) {

    suspend fun getPosts(pageNo: Int) = apis.getPosts(pageNo)
}
