package com.platzi.core.common

import com.platzi.core.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object TestConstants {

    const val title = "Lionel Messi scores again: Inter Miami up 1-0 on FC Dallas in Leagues Cup: Live updates - USA TODAY"
    const val id = "usa-today"
    const val author = "Safid Deen"
    const val content =
        "Messi Mania is hitting the road, and Inter Miami is facing its first deficit in the Lionel Messi era.FC Dallas has a 4-2 lead on Messi and Inter Miami CF on Sunday night in a Round of 16 knockout m… "
    const val publishedAt = "2023-08-07T01:52:30Z"
    const val sourceName = "USA Today"
    const  val url = "https://www.usatoday.com/story/sports/soccer/2023/08/06/is-messi-playing-tonight-inter-miami-vs-fc-dallas-live-updates/70539932007/"
    const val urlToImage = "https://www.usatoday.com/gcdn/authoring/authoring-images/2023/08/07/USAT/70540331007-usatsi-21156967-1.jpg?crop=4590,2581,x210,y229&width=3200&height=1800&format=pjpg&auto=webp"

    val article = Article(
        author,
        content,
        content,
        publishedAt,
        id,
        sourceName,
        title,
        url,
        urlToImage,
        false
    )

    val flowArticles: Flow<List<Article>> = flow { listOf(article) }
}