package com.example.expense_management_app.model.http

import java.math.BigDecimal
import java.util.*

data class BillingEntryHttpRequest(
    var name: String,
    var amount: BigDecimal,
    var billingEntryGroupId: UUID
)
