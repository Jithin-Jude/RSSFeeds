package com.jithinjude.rssfeeds.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            shouldLoad = false
        }

        ivRefresh.setOnClickListener {
            showProgress()
            feedsViewModel.getFeeds()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        shouldLoad = false
    }

    private fun loadFeeds(@Nullable feedList: FeedsModel) {
        hideProgress()
        rvFeeds.adapter = FeedsAdapter(this@FeedsActivity, feedList.channel.item)
        layoutManager = LinearLayoutManager(this)
        rvFeeds.layoutManager = layoutManager
    }

    private fun showProgress(){
        progressBar.visibility = View.VISIBLE
        rvFeeds.visibility = View.INVISIBLE
    }

    private  fun hideProgress(){
        progressBar.visibility = View.GONE
        rvFeeds.visibility = View.VISIBLE
    }
}
