package com.android.paging.networking

import com.android.paging.pojo.*
import retrofit2.http.*

interface PagingApis {

    @GET("/public/v1/posts")
    suspend fun getPosts(@Query("page") page: Int): StandardResponse<Posts>

}