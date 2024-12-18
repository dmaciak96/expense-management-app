package com.example.expense_management_app.mapper

import com.example.expense_management_app.model.dto.BillingEntryGroupDto
import com.example.expense_management_app.model.entity.BillingEntryGroup
import com.example.expense_management_app.model.http.BillingEntryGroupHttpRequest
import com.example.expense_management_app.model.http.BillingEntryGroupHttpResponse

class BillingEntryGroupMapper {
    companion object {
        fun toEntity(dto: BillingEntryGroupDto): BillingEntryGroup = BillingEntryGroup(
            dto.id,
            dto.createdTimestamp,
            dto.lastUpdatedTimestamp,
            dto.version,
            dto.name,
            dto.type
        )


        fun toResponse(dto: BillingEntryGroupDto): BillingEntryGroupHttpResponse = BillingEntryGroupHttpResponse(
            dto.id,
            dto.createdTimestamp,
            dto.lastUpdatedTimestamp,
            dto.version,
            dto.name,
            dto.type
        )

        fun toDto(entity: BillingEntryGroup): BillingEntryGroupDto = BillingEntryGroupDto(
            entity.id,
            entity.createdTimestamp,
            entity.lastUpdatedTimestamp,
            entity.version,
            entity.name,
            entity.type
        )

        fun toDto(request: BillingEntryGroupHttpRequest): BillingEntryGroupDto = BillingEntryGroupDto(
            null,
            null,
            null,
            null,
            request.name,
            request.type
        )
    }
}