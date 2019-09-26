package com.jithinjude.rssfeeds.view

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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
            if(isOnline()) {
                feedsViewModel.getFeeds()
            }else{
                hideProgress(progressBar)
                showMsg("Network not available.")
            }
            shouldLoad = false
        }

        ivRefresh.setOnClickListener {
            if(isOnline()) {
                showProgress(progressBar)
                feedsViewModel.getFeeds()
            }else{
                hideProgress(progressBar)
                showMsg("Network not available.")
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        shouldLoad = false
    }

    private fun loadFeeds(@Nullable feedList: FeedsModel) {
        hideProgress(progressBar)
        rvFeeds.adapter = FeedsAdapter(this@FeedsActivity, feedList.channel.item)
        layoutManager = LinearLayoutManager(this)
        rvFeeds.layoutManager = layoutManager
    }

    private fun showProgress(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        rvFeeds.visibility = View.INVISIBLE
    }

    private  fun hideProgress(progressBar: ProgressBar){
        progressBar.visibility = View.GONE
        rvFeeds.visibility = View.VISIBLE
    }

    private  fun showMsg(message: String){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    private fun isOnline(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}
