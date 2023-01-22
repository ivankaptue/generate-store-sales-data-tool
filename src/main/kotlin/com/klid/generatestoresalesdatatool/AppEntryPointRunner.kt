package com.klid.generatestoresalesdatatool

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

/**
 * @author Ivan Kaptue
 */
@Component
class AppEntryPointRunner(private val generator: GenerateStoreData) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val task1 = CompletableFuture.supplyAsync { generator.generateFile(Pair("MAXI", 5000)) }
        val task2 = CompletableFuture.supplyAsync { generator.generateFile(Pair("METRO", 11000)) }

        CompletableFuture.allOf(task1, task2).join()
    }
}
