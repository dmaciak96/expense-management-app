package com.example.expense_management_app.model.http

import com.example.expense_management_app.model.BillingEntryType

data class BillingEntryGroupHttpRequest(
    val name: String,
    val type: BillingEntryType
)
