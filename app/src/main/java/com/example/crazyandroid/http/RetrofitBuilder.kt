package com.example.crazyandroid.http

import com.example.crazyandroid.data.BaseResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    //const 关键字可以提供编译时常量的优化，例如在编译期间进行常量折叠和内联等操作，以提高性能和减少运行时开销。
    private const val BASE_URL = "https://www.wanandroid.com/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> creat(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    fun <T> BaseResponse<T>.dataConvert(): T
    {
        if (errorCode == 0){
            return  data
        }else{
            throw java.lang.Exception(errorMsg)
        }
    }
}