package org.jesperancinha.keywords.episodes

import org.slf4j.LoggerFactory


val logger: org.slf4j.Logger = LoggerFactory.getLogger(Episode1::class.java)
/**
 * inline
 * in
 * out
 * this
 * init
 * receiver
 */
class Episode1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            processMortgage()
            processMortgage()
        }
    }
}

inline fun processMortgage() = logger.info("Mortgage is being processed...")