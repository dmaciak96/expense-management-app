package com.example.expense_management_app.service

import com.example.expense_management_app.model.dto.BillingEntryDto
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BillingEntryService {

    fun save(request: BillingEntryHttpRequest): BillingEntryDto

    fun update(id: UUID, request: BillingEntryHttpRequest): BillingEntryDto

    fun deleteById(id: UUID)

    fun findAll(pageable: Pageable): Page<BillingEntryDto>

    fun findById(id: UUID): BillingEntryDto
}