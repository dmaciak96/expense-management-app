package com.example.expense_management_app.model.entity

import com.example.expense_management_app.model.BillingEntryType
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class BillingEntryGroup(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @CreatedDate
    var createdTimestamp: Instant?,

    @LastModifiedDate
    var lastUpdatedTimestamp: Instant?,

    @Version
    var version: Int?,

    var name: String,

    @Enumerated(EnumType.STRING)
    var type: BillingEntryType
)
