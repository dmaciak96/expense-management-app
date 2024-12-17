package com.example.expense_management_app.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.*

@Entity
data class BillingEntryGroup(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @CreatedDate
    val createdTimestamp: Instant?,

    @LastModifiedDate
    val lastUpdatedTimestamp: Instant?,

    @Version
    val version: Int?,

    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BillingEntryType
)
