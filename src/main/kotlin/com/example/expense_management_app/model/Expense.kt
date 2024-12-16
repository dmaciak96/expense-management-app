package com.example.expense_management_app.model

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.*


@Entity
data class Expense(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID,

    @CreatedDate
    val createdDate: Instant,

    @LastModifiedDate
    var lastModifiedDate: Instant?,

    @Version
    var version: Int,

    @NotBlank
    var name: String,

    @Min(0)
    var amount: Double,

    @Enumerated(EnumType.STRING)
    var expenseGroup: ExpenseGroup
)
