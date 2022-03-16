package com.example.batchprocessing

import mu.*
import org.springframework.batch.item.ItemProcessor

class PersonItemProcessor : ItemProcessor<Person, Person> {
    companion object {
        private val logger = KotlinLogging.logger {}
    }
    override fun process(item: Person): Person {
        val firstName = item.firstName?.uppercase()
        val lastName = item.lastName?.uppercase()

        val transformedPerson = Person(firstName, lastName)
        logger.info { "Converting($item) into ($transformedPerson)" }

        return transformedPerson
    }

}