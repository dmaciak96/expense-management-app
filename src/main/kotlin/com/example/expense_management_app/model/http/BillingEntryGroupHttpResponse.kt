package com.example.expense_management_app.model.http

import com.example.expense_management_app.model.BillingEntryType
import java.time.Instant
import java.util.*

data class BillingEntryGroupHttpResponse(
    var id: UUID?,
    var createdTimestamp: Instant?,
    var lastUpdatedTimestamp: Instant?,
    var version: Int?,
    var userId: UUID?,
    var name: String,
    var type: BillingEntryType
)
