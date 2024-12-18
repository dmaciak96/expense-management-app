package com.example.expense_management_app.model.http

import com.example.expense_management_app.model.BillingEntryType
import jakarta.validation.constraints.NotBlank

data class BillingEntryGroupHttpRequest(

    @NotBlank
    val name: String,

    val type: BillingEntryType
)
