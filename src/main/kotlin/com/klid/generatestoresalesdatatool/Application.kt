package com.klid.generatestoresalesdatatool

import com.github.javafaker.Faker
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class GenerateStoreSalesDataToolApplication {

    @Bean
    fun faker(): Faker = Faker()
}

fun main(args: Array<String>) {
    runApplication<GenerateStoreSalesDataToolApplication>(*args)
}
