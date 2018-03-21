package de.matoatoa.jsonlogging.fibonacci

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@TraceLogging
@RestController
class FibonacciController (val service: FibonacciService) {
    @GetMapping("fib/{n}")
    fun fib(@PathVariable n : Int) : Int = if(n>1) service.fib(n) else 1
}