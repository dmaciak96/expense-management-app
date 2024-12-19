package com.example.expense_management_app.controller

import com.example.expense_management_app.mapper.BillingEntryGroupMapper
import com.example.expense_management_app.model.http.BillingEntryGroupHttpRequest
import com.example.expense_management_app.model.http.BillingEntryGroupHttpResponse
import com.example.expense_management_app.service.BillingEntryGroupService
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
@RequestMapping("/billing-entry-groups")
class BillingEntryGroupController(private val billingEntryGroupService: BillingEntryGroupService) {

    @GetMapping
    fun findAll(
        @RequestParam("page-number") pageNumber: Int,
        @RequestParam("page-size") pageSize: Int
    ): Page<BillingEntryGroupHttpResponse> {
        return billingEntryGroupService.findAll(PageRequest.of(pageNumber, pageSize)).map {
            BillingEntryGroupMapper.toResponse(it)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): BillingEntryGroupHttpResponse {
        return BillingEntryGroupMapper.toResponse(billingEntryGroupService.findById(id))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody request: BillingEntryGroupHttpRequest): BillingEntryGroupHttpResponse {
        return BillingEntryGroupMapper.toResponse(billingEntryGroupService.save(request))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody request: BillingEntryGroupHttpRequest
    ): BillingEntryGroupHttpResponse {
        return BillingEntryGroupMapper.toResponse(billingEntryGroupService.update(id, request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        billingEntryGroupService.deleteById(id)
    }
}