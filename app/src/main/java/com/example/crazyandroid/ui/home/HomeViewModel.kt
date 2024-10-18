package com.example.crazyandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crazyandroid.data.banner.Banner
import com.example.crazyandroid.http.ApiService
import com.example.crazyandroid.http.RetrofitBuilder
import com.example.crazyandroid.http.RetrofitBuilder.dataConvert
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var banner = MutableLiveData<Banner>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getBanners(){
        // 使用view modelScope.launch主要是防止内存泄露
        viewModelScope.launch {
            try {
                /*dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下下可以节省不少无用代码*/
                var bannerdata = RetrofitBuilder.creat(ApiService::class.java)
                    .getBanner().dataConvert()
                /*给LiveData赋值  ui会自动更新*/
                automaticRotation(bannerdata)
                banner.value = bannerdata
            } catch (e: Exception) {
                /*请求异常的话在这里处理*/
                e.printStackTrace()
            }
        }
    }
    //实现轮播图主要数据结构
    fun automaticRotation(banner: Banner): Banner {
        var lastNode = banner.last()
        var firstNode = banner.first()
        banner.add(0, lastNode)
        banner.add(banner.size, firstNode)
        return banner
    }
}