package com.example.expense_management_app.service

import com.example.expense_management_app.model.dto.BillingEntryGroupDto
import com.example.expense_management_app.model.http.BillingEntryGroupHttpRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BillingEntryGroupService {

    fun save(request: BillingEntryGroupHttpRequest): BillingEntryGroupDto

    fun update(id: UUID, request: BillingEntryGroupHttpRequest): BillingEntryGroupDto

    fun deleteById(id: UUID)

    fun findAll(pageable: Pageable): Page<BillingEntryGroupDto>

    fun findById(id: UUID): BillingEntryGroupDto
}