package com.example.expense_management_app.model.http

import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.util.*

data class BillingEntryHttpRequest(

    @NotBlank
    var name: String,
    var amount: BigDecimal,
    var billingEntryGroupId: UUID
)
