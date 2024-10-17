package com.example.crazyandroid.http

import com.example.crazyandroid.data.BaseResponse
import com.example.crazyandroid.data.banner.Banner
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<Banner>

    //@GET("article/list/{page}/json")
    //suspend fun getArticle(@Path("page")page: Int): BaseResponse<Articleslist>


}