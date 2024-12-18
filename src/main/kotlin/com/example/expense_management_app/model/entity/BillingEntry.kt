package com.example.expense_management_app.model.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class BillingEntry(

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

    var amount: BigDecimal,

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE])
    var billingEntryGroup: BillingEntryGroup
)
