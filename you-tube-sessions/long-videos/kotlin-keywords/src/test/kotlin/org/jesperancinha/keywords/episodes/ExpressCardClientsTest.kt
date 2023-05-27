package org.jesperancinha.keywords.episodes

import org.jesperancinha.keywords.tutorial.ExpressCardClients
import org.junit.jupiter.api.Test

class ExpressCardClientsTest {

    @Test
    fun `should run episode 2 without errors`() {
        ExpressCardClients.main(emptyArray())
    }
}