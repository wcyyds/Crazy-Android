package com.example.crazyandroid.data

data class BaseResponse<T>(
    var data :T,
    var errorCode: Int,
    var errorMsg: String
)