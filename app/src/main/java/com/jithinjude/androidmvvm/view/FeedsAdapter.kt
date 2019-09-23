package com.jithinjude.androidmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jithinjude.androidmvvm.R
import com.jithinjude.androidmvvm.model.FeedsModel
import kotlinx.android.synthetic.main.feed_item.view.*

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */
class FeedsAdapter (
    private val context: Context,
    private val feedList: List<FeedsModel>
): RecyclerView.Adapter<FeedsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = feedList[position].name
        holder.textViewMessage.text = feedList[position].message
        holder.textViewTime.text = feedList[position].time
        Glide.with(context)
            .load(feedList[position].imgUrl)
            .into(holder.imageViewProfilePic)
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var itemFeed = view.cl_feed as ConstraintLayout
        var textViewName = view.tv_name as TextView
        var textViewMessage = view.tv_message as TextView
        var textViewTime = view.tv_time as TextView
        var imageViewProfilePic = view.iv_profile_pic as ImageView
    }
}