package de.matoatoa.jsonlogging.fibonacci

import org.slf4j.MDC
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class FibonacciApplication{

    @Bean
    fun restTemplate(builder: RestTemplateBuilder) = builder.basicAuthorization("USER", "password").build(FibRestTemplate::class.java)!!
}

class FibRestTemplate : RestTemplate()

fun main(args: Array<String>) {
    runApplication<FibonacciApplication>(*args)
}
