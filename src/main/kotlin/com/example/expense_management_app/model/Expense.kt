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
    protected val id: UUID,

    @CreatedDate
    protected val createdDate: Instant,

    @LastModifiedDate
    protected var lastModifiedDate: Instant?,

    @Version
    protected var version: Int,

    @NotBlank
    var name: String,

    @Min(0)
    var amount: Double,

    @Enumerated(EnumType.STRING)
    var expenseGroup: ExpenseGroup
)
