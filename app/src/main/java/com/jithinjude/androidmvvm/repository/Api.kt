package com.jithinjude.androidmvvm.repository

import com.jithinjude.androidmvvm.model.FeedsModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

    var BASE_URL = "https://api.myjson.com/bins/"

interface Api {

    @GET("jkc1v")
    fun getFeeds(): Call<List<FeedsModel>>
}