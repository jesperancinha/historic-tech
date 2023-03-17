package org.jesperancinha.overlays

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class Controller(
    @Autowired
    val service: Service
) {

    @GetMapping("run")
    suspend fun endPointWithServiceCall(): ResponseEntity<String> {
        println("Endpoint run is being access via service of type: ${this.service.javaClass}")
        val whoAmI = service.whoAmI()
        println(whoAmI)
        return ResponseEntity.ok(whoAmI)
    }

    @GetMapping("literal")
    suspend fun endPointWithLiteralReceiverCall(): ResponseEntity<String> {
        val whoAmI = service.call {
            println("Endpoint literal is being access via service of type: ${this.javaClass}")
            whoAmI()
        }
        print(whoAmI)
        return ResponseEntity.ok(whoAmI)
        
    }
}

