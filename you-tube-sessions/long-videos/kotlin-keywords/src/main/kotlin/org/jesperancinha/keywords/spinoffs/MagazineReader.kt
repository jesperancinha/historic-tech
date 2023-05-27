package org.jesperancinha.keywords.spinoffs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

private const val SLEEP_TIME = 700L

class MagazineReader {
    companion object {

        val logger:Logger = LoggerFactory.getLogger(MagazineReader::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            val magazine = """
                There are many articles in this magazine.
                Some are good and some are bad.
                Some are great and some are greater.
                - Article1: Maine Coon shows endless gratitude!
                An owner has reported that his maine coon cat delivers him 1 to 2 wild mice everyday to his doorstep
                - Article2: Maine Coon only likes chique hair dresser
                After several tries this Maine Coon only found this hair dresser to be acceptable. It is the most expensive hairdresser in town.
            """.trimIndent()
            val articleInline1 = findFavouriteArticleInline(magazine)
            logger.info("Favourite inline article1: $articleInline1")
            val articleInline2 = findFavouriteArticleInline(magazine)
            logger.info("Favourite inline article2: $articleInline2")
            val article1 = findFavouriteArticle(magazine)
            logger.info("Favourite article1: $article1")
            val article2 = findFavouriteArticle(magazine)
            logger.info("Favourite article2: $article2")
            val articleInline1WithHelp = findFavouriteArticleInlineWithHelp(magazine){
                substring(0, 10)
            }
            logger.info("Favourite inline article1: $articleInline1WithHelp")
            val articleInline2WithHelp = findFavouriteArticleInlineWithHelp(magazine){
                substring(0, 10)
            }
            logger.info("Favourite inline article2: $articleInline2WithHelp")
            val article1WithHelp = findFavouriteArticleWithHelp(magazine){
                substring(0, 20)
            }
            logger.info("Favourite article1: $article1WithHelp")
            val article2WithHelp = findFavouriteArticleWithHelp(magazine){
                substring(0, 20)
            }
            logger.info("Favourite article2: $article2WithHelp")
        }

        private inline fun findFavouriteArticleInline(magazine: String)
        =  magazine.split("Article").takeLast(2)
            .map { it.split(":")[1].trim() }.random()
            .apply { sleep(SLEEP_TIME) }

        private fun findFavouriteArticle(magazine: String)
        =  magazine.split("Article").takeLast(2)
            .map { it.split(":")[1].trim() }.random()
            .apply { sleep(SLEEP_TIME) }

        private inline fun findFavouriteArticleInlineWithHelp(magazine: String,transform: String.()->String= {this})
        =  magazine.split("Article").takeLast(2)
            .map { it.split(":")[1].trim() }.random()
            .let { transform(it) }
            .apply { sleep(SLEEP_TIME) }

        private fun findFavouriteArticleWithHelp(magazine: String, transform: String.()->String= {this})
        =  magazine.split("Article").takeLast(2)
            .map { it.split(":")[1].trim() }.random()
            .let { transform(it) }
            .apply { sleep(SLEEP_TIME) }
    }
}