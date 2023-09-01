suspend fun main() {

    val webScraper = WebScraper()
    val wordList = webScraper.scrapWords()

    for (word in wordList) {
        println(word)
    }
    val wordDao = WordDao()
    wordDao.saveWordList(wordList)
    println("Words saved to DB succesfully.")
}