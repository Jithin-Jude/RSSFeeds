package com.jithinjude.androidmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jithinjude.androidmvvm.R
import com.jithinjude.androidmvvm.model.FeedsModel
import com.jithinjude.androidmvvm.viewmodel.FeedsViewModel
import kotlinx.android.synthetic.main.activity_feeds.*

class FeedsActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager

    private val feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        feedsViewModel.getFeeds().observe(this, object : Observer<List<FeedsModel>> {
            override fun onChanged(@Nullable feedList: List<FeedsModel>) {
                progressBar.visibility = View.GONE
                rv_feeds.adapter = FeedsAdapter(this@FeedsActivity, feedList)
            }
        })

        layoutManager = LinearLayoutManager(this)
        rv_feeds.layoutManager = layoutManager
    }
}
