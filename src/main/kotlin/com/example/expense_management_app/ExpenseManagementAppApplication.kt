package com.example.expense_management_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ExpenseManagementAppApplication

fun main(args: Array<String>) {
    runApplication<ExpenseManagementAppApplication>(*args)
}
