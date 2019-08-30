package com.jithinjude.androidmvvm

import com.jithinjude.androidmvvm.model.FeedsModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */
interface Api {

    @GET("jkc1v")
    fun getFeeds(): Call<List<FeedsModel>>
}