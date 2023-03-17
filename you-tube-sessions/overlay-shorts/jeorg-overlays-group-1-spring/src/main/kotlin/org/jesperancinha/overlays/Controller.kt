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
    val OService: OService
) {

    @GetMapping("run")
    suspend fun endPointWithServiceCall(): ResponseEntity<String>  {
        val whoAmI = OService.run {
            println("Endpoint run is being access via service of type: $javaClass")
            "I am ${whoAmI()} wrapped by proxy $javaClass)"
        }
        println(whoAmI)
        return ResponseEntity.ok(whoAmI)
    }

    @GetMapping("literal")
    suspend fun endPointWithLiteralReceiverCall(): ResponseEntity<String> {
        val whoAmI = OService.call {
            println("Endpoint literal is being access via service of type: $javaClass")
            "I am ${whoAmI()} but I have no wrapper $javaClass)"
        }
        print(whoAmI)
        return ResponseEntity.ok(whoAmI)
        
    }
}

