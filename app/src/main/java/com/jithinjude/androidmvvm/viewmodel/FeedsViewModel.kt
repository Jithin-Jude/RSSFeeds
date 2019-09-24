package com.jithinjude.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jithinjude.androidmvvm.model.FeedsModel
import androidx.lifecycle.LiveData
import com.jithinjude.androidmvvm.repository.Api
import com.jithinjude.androidmvvm.repository.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

class FeedsViewModel : ViewModel(){

    private var feedList: MutableLiveData<List<FeedsModel>> = MutableLiveData()

    fun getFeeds(): LiveData<List<FeedsModel>> {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        val call = api.getFeeds()

        call.enqueue(object : Callback<List<FeedsModel>> {
            override fun onResponse(call: Call<List<FeedsModel>>, response: Response<List<FeedsModel>>) {
                Log.d("onResponse","RESPONSE:--------"+response.body())
                feedList.setValue(response.body())
            }

            override fun onFailure(call: Call<List<FeedsModel>>, t: Throwable) {
                Log.d("onFailure","ERROR:--------")
            }
        })
        return feedList
    }
}