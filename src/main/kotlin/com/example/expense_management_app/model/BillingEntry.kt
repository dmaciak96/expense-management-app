package com.example.expense_management_app.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.OneToOne
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class BillingEntry(
    id: UUID?,
    createdTimestamp: Instant?,
    lastUpdatedTimestamp: Instant?,
    version: Int?,

    var name: String,

    var amount: BigDecimal,

    @OneToOne(cascade = [CascadeType.ALL])
    var billingEntryGroup: BillingEntryGroup,

    ) : AuditableEntity(id, createdTimestamp, lastUpdatedTimestamp, version)
