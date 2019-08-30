package com.jithinjude.androidmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jithinjude.androidmvvm.R
import com.jithinjude.androidmvvm.model.FeedsModel
import com.jithinjude.androidmvvm.viewmodel.FeedsViewModel
import kotlinx.android.synthetic.main.activity_feeds.*

class FeedsActivity : AppCompatActivity() {

    lateinit var feedsAdapter: FeedsAdapter
    lateinit var layoutManager: LinearLayoutManager

    var feedList: MutableList<FeedsModel> = ArrayList()
    lateinit var feedsModel: FeedsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        val feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel::class.java)

        feedsModel = FeedsModel("Bill Gates","Welcome to Microsoft","10:00 AM","https://upload.wikimedia.org/wikipedia/commons/a/a0/Bill_Gates_2018.jpg")

        feedList.add(feedsModel)

        feedsViewModel.getFeeds().observe(this, object : Observer<List<FeedsModel>> {
            override fun onChanged(@Nullable feedList: List<FeedsModel>) {
                rv_feeds.adapter = FeedsAdapter(this@FeedsActivity, feedList)
            }
        })

        layoutManager = LinearLayoutManager(this)
        rv_feeds.layoutManager = layoutManager
    }
}
