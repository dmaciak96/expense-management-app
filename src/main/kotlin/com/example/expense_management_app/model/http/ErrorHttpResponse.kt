package com.example.expense_management_app.model.http

import java.time.Instant

data class ErrorHttpResponse(val message: String, val creationTimestamp: Instant)
