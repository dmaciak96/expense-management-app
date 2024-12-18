package com.example.expense_management_app.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.*

@MappedSuperclass
abstract class AuditableEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @CreatedDate
    var createdTimestamp: Instant?,

    @LastModifiedDate
    var lastUpdatedTimestamp: Instant?,

    @Version
    var version: Int?
)