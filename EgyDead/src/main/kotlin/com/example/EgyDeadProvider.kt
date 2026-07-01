package com.example

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.network.CloudflareKiller
import com.lagradost.cloudstream3.utils.*
import org.jsoup.Jsoup

class EgyDeadProvider : MainAPI() {
    override var mainUrl = "https://tv9.egydead.live"
    override var name = "EgyDead"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries, TvType.Anime)

    // This tells Cloudstream to use a custom interceptor for Cloudflare
    private val cfInterceptor = CloudflareKiller()

    override suspend fun search(query: String): List<SearchResponse> {
        // We pass the interceptor into the request so it auto-solves or pulls app cookies
        val response = app.get(
            "$mainUrl/?s=$query", 
            interceptor = cfInterceptor
        ).text
        
        val document = Jsoup.parse(response)
        
        // TODO: Map the html search cards here
        return emptyList()
    }
}
