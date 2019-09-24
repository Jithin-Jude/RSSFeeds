package com.jithinjude.rssfeeds.model

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */

data class FeedsModel(
    var channel: Channel
)

data class Channel(
    var title: String,

    var link: String,

    var description: String,

    var language: String,

    var item: MutableList<Item>
)

data class Item(
    var title: String,

    var link: String,

    var description: String
)