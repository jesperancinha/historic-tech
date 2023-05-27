package org.jesperancinha.keywords.episodes

import org.jesperancinha.keywords.tutorial.TechnicalBanking
import org.junit.jupiter.api.Test

class TechnicalBankingTest {

    @Test
    fun `should run episode 4 without errors`() {
        TechnicalBanking.main(emptyArray())
    }
}