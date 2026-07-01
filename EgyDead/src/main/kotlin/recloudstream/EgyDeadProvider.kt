package recloudstream

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import org.jsoup.Jsoup

class EgyDeadProvider : MainAPI() {
    override var mainUrl = "https://tv9.egydead.live"
    override var name = "EgyDead"
    override var lang = "ar" 
    override val hasMainPage = false 
    override val hasQuickSearch = true
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime
    )

    override suspend fun search(query: String): List<SearchResponse> {
        // Standard app.get call without the interceptor
        val response = app.get("$mainUrl/?s=$query").text
        val document = Jsoup.parse(response)
        
        // Target standard movie/series grid cards
        val elements = document.select("div.box-item, div.result-item, article, div.movie-item, a.box")
        
        return elements.mapNotNull { element ->
            val title = element.selectFirst("h2, h3, .title, .entry-title, .box-title")?.text() ?: return@mapNotNull null
            val url = element.selectFirst("a")?.attr("href") ?: return@mapNotNull null
            
            // Handles both normal images and lazy-loaded ones
            val poster = element.selectFirst("img")?.attr("data-src") 
                ?: element.selectFirst("img")?.attr("src")

            newMovieSearchResponse(title, url, TvType.Movie) {
                this.posterUrl = poster
            }
        }
    }
}
