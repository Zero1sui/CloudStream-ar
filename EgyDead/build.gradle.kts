import com.lagradost.cloudstream3.gradle.CloudstreamExtension

// Use an integer for version numbers
version = 1

cloudstream {
    description = "Watch OUT DAWG!"
    authors = listOf("ZERO")

    /**
     * Status int as one of the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta-only
     **/
    status = 3 

    tvTypes = listOf("Movie", "TvSeries", "Anime")
    // you can swap this icon url out later for an egydead logo if you want
    iconUrl = "https://tv9.egydead.live/favicon.ico"

    isCrossPlatform = true
}
