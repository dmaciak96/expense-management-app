package com.example.expense_management_app.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
data class BillingEntry(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @CreatedDate
    var createdTimestamp: Instant?,

    @LastModifiedDate
    var lastUpdatedTimestamp: Instant?,

    @Version
    var version: Int?,

    val name: String,

    val amount: BigDecimal,

    @OneToOne(cascade = [CascadeType.ALL])
    val billingEntryGroup: BillingEntryGroup,
)
