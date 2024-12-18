package com.example.expense_management_app.model

import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class BillingEntryGroup(
    id: UUID?,
    createdTimestamp: Instant?,
    lastUpdatedTimestamp: Instant?,
    version: Int?,
    var name: String,

    @Enumerated(EnumType.STRING)
    var type: BillingEntryType

) : AuditableEntity(id, createdTimestamp, lastUpdatedTimestamp, version)
