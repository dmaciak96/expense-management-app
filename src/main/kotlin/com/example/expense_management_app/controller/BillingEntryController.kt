package com.example.expense_management_app.controller

import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.model.http.BillingEntryHttpResponse
import com.example.expense_management_app.service.BillingEntryService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/billing-entries")
class BillingEntryController(private val billingEntryService: BillingEntryService) {

    @GetMapping
    fun findAll(
        @RequestParam("page-number") pageNumber: Int,
        @RequestParam("page-size") pageSize: Int
    ): Page<BillingEntryHttpResponse> {
        throw NotImplementedError()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<BillingEntryHttpResponse> {
        throw NotImplementedError()
    }

    @PostMapping
    fun save(@Valid @RequestBody request: BillingEntryHttpRequest): ResponseEntity<BillingEntryHttpResponse> {
        throw NotImplementedError()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody request: BillingEntryHttpRequest
    ): ResponseEntity<BillingEntryHttpResponse> {
        throw NotImplementedError()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        throw NotImplementedError()
    }
}