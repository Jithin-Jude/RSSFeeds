package com.jithinjude.rssfeeds.view

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
import me.toptas.rssconverter.RssFeed

class FeedsActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        val feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel::class.java)

        feedsViewModel.getFeeds().observe(this, object : Observer<RssFeed> {
            override fun onChanged(@Nullable feedList: RssFeed) {
                progressBar.visibility = View.GONE
                rv_feeds.adapter = FeedsAdapter(this@FeedsActivity, feedList.items!!)
            }
        })

        layoutManager = LinearLayoutManager(this)
        rv_feeds.layoutManager = layoutManager
    }
}
