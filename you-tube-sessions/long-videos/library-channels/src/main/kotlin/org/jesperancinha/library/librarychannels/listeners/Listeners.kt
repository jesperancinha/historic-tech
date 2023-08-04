package org.jesperancinha.library.librarychannels.listeners

import org.springframework.boot.context.event.ApplicationFailedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserRemovedListener {

    @EventListener
    fun handleUserRemovedEvent(event: ApplicationFailedEvent) {
        println("Failed to initiate service! $event")
    }

}