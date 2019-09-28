package com.jithinjude.rssfeeds.view

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jithinjude.rssfeeds.R
import com.jithinjude.rssfeeds.model.Item
import kotlinx.android.synthetic.main.feed_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */
class FeedsAdapter (
    private val context: Context,
    private val feedList: MutableList<Item>
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
        holder.textViewTitle.text = feedList[position].title
        holder.textViewPubDate.text = formatDates(feedList[position].pubDate)
        holder.textViewDescription.text = removeHtmlTags(feedList[position].description)
        Glide.with(context)
            .load(feedList[position].enclosure.url)
            .into(holder.imageViewFeedImg)
    }

    fun removeHtmlTags(htmlString: String): String {
        //Remove HTML tags
        return Html.fromHtml(htmlString).toString()
    }

    fun formatDates(date: String): String{
        lateinit var forDate: String
        val givenDateString = date.trim()
        val sdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
        try {
            val mDate = sdf.parse(givenDateString)
            val sdf2 = SimpleDateFormat("EEEE, dd MMMM yyyy - hh:mm a", Locale.ENGLISH)
            forDate = sdf2.format(mDate)

        } catch (e: ParseException) {
            e.printStackTrace()

        }
        return  forDate
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var itemFeed = view.clFeed as ConstraintLayout
        var textViewTitle = view.tvTitle as TextView
        var textViewPubDate = view.tvPubDate as TextView
        var textViewDescription = view.tvDescription as TextView
        var imageViewFeedImg = view.ivFeedImg as ImageView
    }
}