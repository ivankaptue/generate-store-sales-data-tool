package com.klid.generatestoresalesdatatool

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author Ivan Kaptue
 */
@Component
class AppEntryPointRunner : CommandLineRunner {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AppEntryPointRunner::class.java)
    }

    override fun run(vararg args: String?) {
        for(i in 0..10) {
            logger.info("Display index $i")
        }
    }
}
