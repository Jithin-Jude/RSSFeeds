package com.jithinjude.rssfeeds.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jithinjude.rssfeeds.model.FeedsModel
import androidx.lifecycle.LiveData
import com.jithinjude.rssfeeds.repository.Api
import com.jithinjude.rssfeeds.repository.BASE_URL
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

class FeedsViewModel : ViewModel(){

    private var feedList: MutableLiveData<RssFeed> = MutableLiveData()

    fun getFeeds(): LiveData<RssFeed> {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(RssConverterFactory.create())
            .build()

        val service = retrofit.create(Api::class.java)
        service.getFeeds()
            .enqueue(object : Callback<RssFeed> {
                override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                    Log.d("onResponse","RESPONSE:--------"+response.body())
                    feedList.setValue(response.body())
                }

                override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                    // Show failure message
                }
            })
        return feedList
    }
}