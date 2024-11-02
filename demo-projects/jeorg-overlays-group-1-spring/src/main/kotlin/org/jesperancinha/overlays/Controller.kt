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
    val openService: OpenService,
) {

    @GetMapping("run")
    suspend fun endPointWithServiceCall(): ResponseEntity<String> {
        println("---- run")
        val whoAmI = openService.run {
            println("Endpoint run is being access via service of type: $javaClass")
            "I am ${whoAmI()} wrapped by proxy $javaClass)"
        }
        println(whoAmI)
        return ResponseEntity.ok(whoAmI)
    }

    @GetMapping("literal")
    suspend fun endPointWithLiteralReceiverCall(): ResponseEntity<String> {
        println("---- literal")
        val whoAmI = openService.call {
            println("Endpoint literal is being access via service of type: $javaClass")
            "I am ${whoAmI()} but I have no wrapper $javaClass)"
        }
        println(whoAmI)
        return ResponseEntity.ok(whoAmI)

    }

    @GetMapping("literal/works")
    suspend fun endPointWithLiteralReceiverCallThatWorks(): ResponseEntity<String> {
        println("---- literal/works")
        val whoAmI = openService.callWorks {
            println("Endpoint literal is being access via service of type: $javaClass")
            "I am ${whoAmI()} but now I have a wrapper $javaClass)"
        }
        println(whoAmI)
        return ResponseEntity.ok(whoAmI)

    }
}

