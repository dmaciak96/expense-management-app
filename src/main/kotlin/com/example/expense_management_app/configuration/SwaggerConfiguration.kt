package com.example.expense_management_app.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration(private val buildProperties: BuildProperties) {

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .title(buildProperties.name)
            .version(buildProperties.version)
        return OpenAPI().info(info)
    }
}