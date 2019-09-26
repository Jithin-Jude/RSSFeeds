package com.jithinjude.rssfeeds.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by <Jithin/Jude> on 30,August,2019.
 * jithin.jude68@gmail.com
 */



@Root(strict = false, name = "rss")
data class FeedsModel(
    @field:Element(name = "channel", required = false)
    @param:Element(name = "channel", required = false)
    var channel: Channel
)

@Root(strict = false, name = "channel")
data class Channel(
    @field:Element(name = "title", required = false)
    @param:Element(name = "title", required = false)
    var title: String,

    @field:Element(name = "link", required = false)
    @param:Element(name = "link", required = false)
    var link: String,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    var description: String,

    @field:Element(name = "lastBuildDate", required = false)
    @param:Element(name = "lastBuildDate", required = false)
    var lastBuildDate: String,

    @field:Element(name = "language", required = false)
    @param:Element(name = "language", required = false)
    var language: String,

    @field:Element(name = "docs", required = false)
    @param:Element(name = "docs", required = false)
    var docs: String,

    @field:ElementList(inline = true, required = false)
    @param:ElementList(inline = true, required = false)
    var item: List<Item>
)

@Root(strict = false, name = "item")
data class Item(
    @field:Element(name = "title", required = false)
    @param:Element(name = "title", required = false)
    var title: String,

    @field:Element(name = "link", required = false)
    @param:Element(name = "link", required = false)
    var link: String,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    var description: String,

    @field:Element(name = "guid", required = false)
    @param:Element(name = "guid", required = false)
    var guid: String,

    @field:Element(name = "pubDate", required = false)
    @param:Element(name = "pubDate", required = false)
    var pubDate: String,

    @field:Element(name = "enclosure", required = false)
    @param:Element(name = "enclosure", required = false)
    var enclosure: Enclosure
)

@Root(strict = false, name = "enclosure")
data class Enclosure(
    @field:Attribute(name = "url", required = false)
    @param:Attribute(name = "url", required = false)
    var url: String
)