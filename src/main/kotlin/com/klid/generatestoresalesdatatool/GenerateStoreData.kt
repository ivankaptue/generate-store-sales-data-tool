package com.klid.generatestoresalesdatatool

import com.github.javafaker.Faker
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.math.BigDecimal

/**
 * @author Ivan Kaptue
 */
@Component
class GenerateStoreData(private val faker: Faker) {

    companion object {
        private val logger = LoggerFactory.getLogger(GenerateStoreData::class.java)
        private const val SEPARATOR = ","
        private const val GENERATED_DIR = "src/main/resources/generated/"
    }

    /**
     * Generate csv file for store with sales
     */
    fun generateFile(params: Pair<String, Int>) {
        validateArguments(params)
        createOutputDir()
        val content = generateSales(params)
        writeContentToFile(params, content)
    }

    private fun generateSales(params: Pair<String, Int>): java.lang.StringBuilder {
        logger.info("Start generate sales $params")
        val content = StringBuilder("Product,Quantity,Price").append("\n")

        var product: String
        var quantity: Int
        var price: BigDecimal

        for (i in 1..params.second) {
            product = faker.commerce().productName()
            quantity = faker.random().nextInt(2, 150)
            price = BigDecimal(faker.commerce().price(100.0, 20000.0))

            content.append("$product$SEPARATOR$quantity$SEPARATOR$price".uppercase()).append("\n")
        }

        logger.info("End generate sales : $params")

        return content
    }

    private fun writeContentToFile(params: Pair<String, Int>, content: StringBuilder) {
        val fileName = "${params.first}${System.currentTimeMillis()}.csv".uppercase()

        logger.info("Start write generated data to file : $fileName, params: $params")

        BufferedWriter(FileWriter("${GENERATED_DIR}$fileName", Charsets.UTF_8, false))
                .use { writer ->
                    writer.write(content.toString())
                }

        logger.info("End writing date to file. params: $params")
    }

    fun validateArguments(params: Pair<String, Int>) {
        if (params.first.isBlank()) {
            throw IllegalArgumentException("Store name must be defined. params: $params")
        }

        if (params.second < 1) {
            throw IllegalArgumentException("sales count must be positive. params: $params")
        }
    }

    fun createOutputDir() {
        logger.info("Create output dir")
        val file = File(GENERATED_DIR)
        if (!file.exists() && file.mkdirs()) {
            logger.info("Ouput dir created")
            return
        }
        logger.info("Output dir already exists")
    }
}
