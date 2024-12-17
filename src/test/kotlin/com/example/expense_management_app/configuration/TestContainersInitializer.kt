package com.example.expense_management_app.configuration

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class TestContainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext>, AfterAllCallback {

    companion object {
        private const val DATABASE_CONTAINER_NAME = "postgres:14.1-alpine"
        private val DATABASE_CONTAINER = PostgreSQLContainer(DATABASE_CONTAINER_NAME)
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres")
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        DATABASE_CONTAINER.start();

        TestPropertyValues.of(
            "spring.datasource.url=" + DATABASE_CONTAINER.jdbcUrl,
            "spring.datasource.username=" + DATABASE_CONTAINER.username,
            "spring.datasource.password=" + DATABASE_CONTAINER.password
        ).applyTo(applicationContext.environment)
    }

    override fun afterAll(context: ExtensionContext?) {
        if (DATABASE_CONTAINER == null) {
            return
        }
        DATABASE_CONTAINER.close()
    }
}