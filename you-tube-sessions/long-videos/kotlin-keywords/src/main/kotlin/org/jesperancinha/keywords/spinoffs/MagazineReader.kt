package org.jesperancinha.keywords.spinoffs

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MagazineReader {
    companion object {

        val logger:Logger = LoggerFactory.getLogger(MagazineReader::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            val magazine1 = """
                There are many articles in this magazine.
                Some are good and some are bad.
                Some are great and some are greater.
                - Article1: Maine Coon shows endless gratitude!
                An owner has reported that his maine coon cat delivers him 1 to 2 wild mice everyday to his doorstep
                - Article2: Maine Coon only likes chique hair dresser
                After several tries this Maine Coon only found this hair dresser to be acceptable. It is the most expensive hairdresser in town.
            """.trimIndent()
            val article1 = findFavouriteArticle(magazine1)
            val article2 = findFavouriteArticle(magazine1)
            logger.info("Favourite article1: $article1")
            logger.info("Favourite article2: $article2")
        }

        inline private fun findFavouriteArticle(magazine: String)
        =  magazine.split("Article").takeLast(2)
            .map { it.split(":")[1].trim() }.random()
    }
}