package de.matoatoa.jsonlogging.fibonacci

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@TraceLogging
@Service
class FibonacciService(val restTemplate: RestTemplate) {
    fun fib(n: Int): Int = if (n < 2) call(n) else call(n - 1) + call(n - 2)
    private fun call(n: Int) = restTemplate.getForObject(URI("http://localhost:9876/fib/$n"), Int::class.java)!!
}
