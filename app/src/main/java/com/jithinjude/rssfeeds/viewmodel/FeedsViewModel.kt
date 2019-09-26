package com.jithinjude.rssfeeds.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jithinjude.rssfeeds.model.FeedsModel
import androidx.lifecycle.LiveData
import com.jithinjude.rssfeeds.repository.Api
import com.jithinjude.rssfeeds.repository.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

class FeedsViewModel : ViewModel(){

    var feedsLiveData: MutableLiveData<FeedsModel> = MutableLiveData()

    fun getFeeds(): LiveData<FeedsModel> {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val service = retrofit.create(Api::class.java)
        service.getFeeds()
            .enqueue(object : Callback<FeedsModel> {
                override fun onResponse(call: Call<FeedsModel>, response: Response<FeedsModel>) {
                    Log.d("onResponse","RESPONSE:--------"+response.body())
                    feedsLiveData.setValue(response.body())
                }

                override fun onFailure(call: Call<FeedsModel>, t: Throwable) {
                    Log.d("onFailure","ERROR:--------"+t.toString())
                }
            })
        return feedsLiveData
    }
}