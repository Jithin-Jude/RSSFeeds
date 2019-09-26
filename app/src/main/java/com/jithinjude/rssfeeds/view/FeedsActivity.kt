package com.jithinjude.rssfeeds.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jithinjude.rssfeeds.R
import com.jithinjude.rssfeeds.model.FeedsModel
import com.jithinjude.rssfeeds.viewmodel.FeedsViewModel
import kotlinx.android.synthetic.main.activity_feeds.*

var shouldLoad = true

class FeedsActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager

    private val feedsViewModel: FeedsViewModel by lazy {
        ViewModelProviders.of(this).get(FeedsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        feedsViewModel.feedsLiveData.observe(this, Observer {
            it?.let {
                loadFeeds(it)
            }
        })

        if(shouldLoad){
            feedsViewModel.getFeeds()
            Toast.makeText(this,"ViewModel",Toast.LENGTH_SHORT).show()
            shouldLoad = false
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        shouldLoad = false
    }
    private fun loadFeeds(@Nullable feedList: FeedsModel) {
        progressBar.visibility = View.GONE
        rv_feeds.adapter = FeedsAdapter(this@FeedsActivity, feedList.channel.item)
        layoutManager = LinearLayoutManager(this)
        rv_feeds.layoutManager = layoutManager
    }
}
