package com.example.batchprocessing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.*

@SpringBootApplication
class BatchProcessingApplication

fun main(args: Array<String>) {
    runApplication<BatchProcessingApplication>(*args)
    exitProcess(0)
}
