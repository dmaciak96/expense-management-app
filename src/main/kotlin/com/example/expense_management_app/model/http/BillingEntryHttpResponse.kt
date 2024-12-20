package com.example.expense_management_app.model.http

import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class BillingEntryHttpResponse(
    var id: UUID?,
    var createdTimestamp: Instant?,
    var lastUpdatedTimestamp: Instant?,
    var version: Int?,
    var userId: UUID?,
    var name: String,
    var amount: BigDecimal,
    var billingEntryGroup: BillingEntryGroupHttpResponse?
)
