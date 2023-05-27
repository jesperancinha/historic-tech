package org.jesperancinha.keywords.episodes

import org.jesperancinha.keywords.tutorial.CardsAndMortgages
import org.junit.jupiter.api.Test

class CardsAndMortgagesTest {

    @Test
    fun `should run cards and mortgages without errors`() {
        CardsAndMortgages.main(emptyArray())
    }
}