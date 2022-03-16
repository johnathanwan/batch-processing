package com.example.batchprocessing

import mu.*
import org.springframework.batch.core.*
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Suppress("SqlResolve")
@Component
class JobCompletionNotificationListener(val jdbcTemplate: JdbcTemplate) : JobExecutionListenerSupport() {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.status == BatchStatus.COMPLETED) {
            logger.info { "!!! JOB FINISHED! Time to verify the results" }

            val rowMapper = RowMapper<Person> {
                    rs: ResultSet,_: Int -> Person(rs.getString(1),rs.getString(2))}

            jdbcTemplate.query("SELECT first_name, last_name FROM people",rowMapper)
                .forEach { logger.info { "Found<$it> in the database." } }
        }
    }
}