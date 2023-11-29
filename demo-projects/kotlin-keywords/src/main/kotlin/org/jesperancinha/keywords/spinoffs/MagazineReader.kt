package org.jesperancinha.keywords.spinoffs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis

private const val SLEEP_TIME = 6000L

// @formatter:off

class MagazineReader {
    companion object {

        private val logger: Logger = LoggerFactory
            .getLogger(MagazineReader::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            logger.info(
                "Took ${
                    measureTimeMillis {
                        val magazine = """
                There are many articles in this magazine.
                Some are good and some are bad.
                Some are great and some are greater.
                - Article1: Maine Coon shows endless gratitude!
                An owner has reported that his maine coon
                 cat delivers him 1 to 2 wild mice 
                 everyday to his doorstep
                - Article2: Maine Coon only likes chique hair dresser
                After several tries this Maine Coon only found this
                 hair dresser to be acceptable. 
                 It is the most expensive hairdresser 
                 in town.
            """.trimIndent()
                        val articleInline1 
                            = findFavouriteArticleInline(magazine, 0)
                        logger.info("Favourite inline article1: $articleInline1")
                        val articleInline2 
                        = findFavouriteArticleInline(magazine, 1)
                        logger.info("Favourite inline article2: $articleInline2")
                        val article1 = findFavouriteArticle(magazine, 0)
                        logger.info("Favourite article1: $article1")
                        val article2 = findFavouriteArticle(magazine, 1)
                        logger.info("Favourite article2: $article2")
                        val articleInline1WithHelp 
                            = findFavouriteArticleInlineWithHelp(magazine, 0) { 
                                substring(0, 10)
                        }
                        logger.info("Favourite inline article1: $articleInline1WithHelp")
                        val articleInline2WithHelp 
                            = findFavouriteArticleInlineWithHelp(magazine, 1) { 
                                substring(0, 10)
                        }
                        logger.info("Favourite inline article2: $articleInline2WithHelp")
                        val article1WithHelp 
                            = findFavouriteArticleWithHelp(magazine, 0) { 
                                substring(0, 20)
                        }
                        logger.info("Favourite article1: $article1WithHelp")
                        val article2WithHelp 
                            = findFavouriteArticleWithHelp(magazine, 1) { 
                                substring(0, 20)
                        }
                        logger.info("Favourite article2: $article2WithHelp")
                    } / 1000
                }s to execute"
            )
        }

        private inline fun findFavouriteArticleInline(magazine: String, i: Int)
            = magazine
                .split("Article").takeLast(2)
                .map {
                    it.split(":")[1]
                        .trimArticleEnds()
                }[i]
                .apply { sleep(SLEEP_TIME) }

        private fun findFavouriteArticle(magazine: String, i: Int)
            = magazine
                .split("Article").takeLast(2)
                .map {
                    it.split(":")[1]
                        .trimArticleEnds()
                }[i]
                .apply { sleep(SLEEP_TIME) }

        private inline fun findFavouriteArticleInlineWithHelp(
            magazine: String,
            i: Int,
            transform: String.() -> String = { this }
        ) = magazine.split("Article").takeLast(2)
                .map {
                    it
                        .split(":")[1]
                        .trimArticleEnds()
                }[i]
                .let { transform(it) }
                .apply { sleep(SLEEP_TIME) }

        private fun findFavouriteArticleWithHelp(
            magazine: String, i: Int,
            transform: String.() -> String = { this }
        ) =
            magazine
                .split("Article").takeLast(2)
                .map {
                    it
                        .split(":")[1]
                        .trimArticleEnds()
                }[i]
                .let { transform(it) }
                .apply { sleep(SLEEP_TIME) }
    }
}

private fun String.trimArticleEnds() = trim().replace("\n-","")

// @formatter:on
