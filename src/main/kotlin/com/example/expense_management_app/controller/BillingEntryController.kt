package com.example.expense_management_app.controller

import com.example.expense_management_app.mapper.BillingEntryMapper
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.model.http.BillingEntryHttpResponse
import com.example.expense_management_app.service.BillingEntryService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
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
        return billingEntryService.findAll(PageRequest.of(pageNumber, pageSize)).map {
            BillingEntryMapper.toResponse(it)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): BillingEntryHttpResponse {
        return BillingEntryMapper.toResponse(billingEntryService.findById(id))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody request: BillingEntryHttpRequest): BillingEntryHttpResponse {
        return BillingEntryMapper.toResponse(billingEntryService.save(request))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody request: BillingEntryHttpRequest
    ): BillingEntryHttpResponse {
        return BillingEntryMapper.toResponse(billingEntryService.update(id, request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        billingEntryService.deleteById(id)
    }
}