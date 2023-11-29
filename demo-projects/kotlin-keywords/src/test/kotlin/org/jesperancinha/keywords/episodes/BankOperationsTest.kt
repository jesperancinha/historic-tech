package org.jesperancinha.keywords.episodes

import org.jesperancinha.keywords.tutorial.BankOperations
import org.junit.jupiter.api.Test

class BankOperationsTest {

    @Test
    fun `should run episode 3 without errors`() {
        BankOperations.main(emptyArray())
    }
}