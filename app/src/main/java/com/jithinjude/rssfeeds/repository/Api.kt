package com.jithinjude.rssfeeds.repository

import com.jithinjude.rssfeeds.model.FeedsModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

    var BASE_URL = "http://www.cinemablend.com/"

interface Api {

    @GET("rss_review.php")
    fun getFeeds(): Call<FeedsModel>
}