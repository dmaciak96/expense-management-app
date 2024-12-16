package com.example.expense_management_app.model

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @CreatedDate
    val createdDate: Instant?,

    @LastModifiedDate
    var lastModifiedDate: Instant?,

    @Version
    var version: Int?,

    @NotBlank
    var name: String,

    @Min(0)
    var amount: Double,

    @Enumerated(EnumType.STRING)
    var expenseGroup: ExpenseGroup
)
