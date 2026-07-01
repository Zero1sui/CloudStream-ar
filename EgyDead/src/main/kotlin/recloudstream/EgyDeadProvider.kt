package recloudstream

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.network.CloudflareKiller
import com.lagradost.cloudstream3.utils.*
import org.jsoup.Jsoup

class EgyDeadProvider : MainAPI() {
    override var mainUrl = "https://tv9.egydead.live"
    override var name = "EgyDead"
    override var lang = "ar" // Setting it to Arabic since it's an Arabic site
    override val hasMainPage = false // keeping it false until we map the home page layout
    override val hasQuickSearch = true
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime
    )

    private val cfInterceptor = CloudflareKiller()

    override suspend fun search(query: String): List<SearchResponse> {
        val response = app.get("$mainUrl/?s=$query", interceptor = cfInterceptor).text
        val document = Jsoup.parse(response)
        
        // This is where we will map the elements using document.select()
        return emptyList()
    }
}
