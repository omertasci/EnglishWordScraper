package busines

import constant.AppConstant.Companion.BASE_URL
import constant.AppConstant.Companion.wordlists
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import model.WordData
import org.jsoup.Jsoup


class WebScraper {

    suspend fun scrapWords() : List<WordData> {
        val wordList = mutableListOf<WordData>()
        val url = BASE_URL + wordlists

        HttpClient(CIO).use { client ->

            val response: HttpResponse = client.get(url)

            val html = response.bodyAsText()

            val document = Jsoup.parse(html)
            val table = document.select("div#wordlistsContentPanel").first()

            table?.select("ul li")?.forEach { row ->
                var code="";
                try {
                    val a = row.select("a")
                    val span = row.select("span")

                    code = row.attributes().get("data-hw")
                    val definition = a[0].text()
                    val type = span[0].text()
                    val level = span[1].text().ifBlank { "" }
                    val link = a.attr("href").ifBlank { "" }

                    wordList.add(WordData(code, definition, type, level, link))
                } catch (ex:IndexOutOfBoundsException){
                    println(code)
                    ex.printStackTrace()
                }

            }
        }
        return wordList
    }

/*    companion object {
        fun scrapWords():List<model.WordData> {
            return scrapWords()
        }
    }*/


}