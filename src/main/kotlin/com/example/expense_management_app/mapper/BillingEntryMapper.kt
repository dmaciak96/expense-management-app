package com.example.expense_management_app.mapper

import com.example.expense_management_app.model.dto.BillingEntryDto
import com.example.expense_management_app.model.entity.BillingEntry
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.model.http.BillingEntryHttpResponse

class BillingEntryMapper {
    companion object {
        fun toEntity(dto: BillingEntryDto): BillingEntry = BillingEntry(
            dto.id,
            dto.createdTimestamp,
            dto.lastUpdatedTimestamp,
            dto.version,
            dto.name,
            dto.amount,
            dto.billingEntryGroup?.let { BillingEntryGroupMapper.toEntity(it) }
        )

        fun toResponse(dto: BillingEntryDto): BillingEntryHttpResponse = BillingEntryHttpResponse(
            dto.id,
            dto.createdTimestamp,
            dto.lastUpdatedTimestamp,
            dto.version,
            dto.name,
            dto.amount,
            dto.billingEntryGroup?.let { BillingEntryGroupMapper.toResponse(it) }
        )

        fun toDto(entity: BillingEntry): BillingEntryDto = BillingEntryDto(
            entity.id,
            entity.createdTimestamp,
            entity.lastUpdatedTimestamp,
            entity.version,
            entity.name,
            entity.amount,
            entity.billingEntryGroup?.let { BillingEntryGroupMapper.toDto(it) }
        )

        fun toDto(request: BillingEntryHttpRequest): BillingEntryDto = BillingEntryDto(
            null,
            null,
            null,
            null,
            request.name,
            request.amount,
            null
        )
    }
}