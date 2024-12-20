package com.example.expense_management_app.mapper

import com.example.expense_management_app.model.BillingEntryType
import com.example.expense_management_app.model.dto.BillingEntryDto
import com.example.expense_management_app.model.dto.BillingEntryGroupDto
import com.example.expense_management_app.model.entity.BillingEntry
import com.example.expense_management_app.model.entity.BillingEntryGroup
import com.example.expense_management_app.model.http.BillingEntryGroupHttpResponse
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.model.http.BillingEntryHttpResponse
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Instant
import java.util.*
import kotlin.test.assertEquals

class BillingEntryMapperTest {

    companion object {
        private val BILLING_ENTRY_ID = UUID.randomUUID()
        private val BILLING_ENTRY_CREATED_TIMESTAMP = Instant.now()
        private val BILLING_ENTRY_LAST_UPDATE_TIMESTAMP = Instant.now()
        private val BILLING_ENTRY_AMOUNT = BigDecimal("123123123123123123123123123123")
        private val BILLING_ENTRY_VERSION = 12
        private val BILLING_ENTRY_NAME = "BILLING_ENTRY"
        private val USER_ID = UUID.randomUUID()

        private val BILLING_ENTRY_GROUP_ID = UUID.randomUUID()
        private val BILLING_ENTRY_GROUP_CREATED_TIMESTAMP = Instant.now()
        private val BILLING_ENTRY_GROUP_LAST_UPDATE_TIMESTAMP = Instant.now()
        private val BILLING_ENTRY_GROUP_TYPE = BillingEntryType.EXPENSE
        private val BILLING_ENTRY_GROUP_VERSION = 15
        private val BILLING_ENTRY_GROUP_NAME = "BILLING_ENTRY_GROUP"
    }

    @Test
    fun shouldMapEntityToDto() {
        val entity = createBillingEntryEntity()
        val result = BillingEntryMapper.toDto(entity)
        assertEquals(createBillingEntryDto(), result)
    }

    @Test
    fun shouldMapDtoToEntity() {
        val dto = createBillingEntryDto()
        val result = BillingEntryMapper.toEntity(dto)
        assertEquals(createBillingEntryEntity(), result)
    }

    @Test
    fun shouldMapRequestToDto() {
        val request = createBillingEntryHttpRequest()
        val expected = createBillingEntryDto().copy(
            id = null,
            createdTimestamp = null,
            lastUpdatedTimestamp = null,
            version = null,
            userId = null,
            billingEntryGroup = null
        )
        val result = BillingEntryMapper.toDto(request)
        assertEquals(expected, result)
    }

    @Test
    fun shouldMapDtoToResponse() {
        val dto = createBillingEntryDto()
        val result = BillingEntryMapper.toResponse(dto)
        assertEquals(createBillingEntryHttpResponse(), result)
    }

    private fun createBillingEntryEntity(): BillingEntry {
        return BillingEntry(
            BILLING_ENTRY_ID,
            BILLING_ENTRY_CREATED_TIMESTAMP,
            BILLING_ENTRY_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_VERSION,
            USER_ID,
            BILLING_ENTRY_NAME,
            BILLING_ENTRY_AMOUNT,
            createBillingEntryGroupEntity()
        )
    }

    private fun createBillingEntryGroupEntity(): BillingEntryGroup {
        return BillingEntryGroup(
            BILLING_ENTRY_GROUP_ID,
            BILLING_ENTRY_GROUP_CREATED_TIMESTAMP,
            BILLING_ENTRY_GROUP_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_GROUP_VERSION,
            USER_ID,
            BILLING_ENTRY_GROUP_NAME,
            BILLING_ENTRY_GROUP_TYPE
        )
    }

    private fun createBillingEntryDto(): BillingEntryDto {
        return BillingEntryDto(
            BILLING_ENTRY_ID,
            BILLING_ENTRY_CREATED_TIMESTAMP,
            BILLING_ENTRY_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_VERSION,
            USER_ID,
            BILLING_ENTRY_NAME,
            BILLING_ENTRY_AMOUNT,
            createBillingEntryGroupDto()
        )
    }

    private fun createBillingEntryGroupDto(): BillingEntryGroupDto {
        return BillingEntryGroupDto(
            BILLING_ENTRY_GROUP_ID,
            BILLING_ENTRY_GROUP_CREATED_TIMESTAMP,
            BILLING_ENTRY_GROUP_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_GROUP_VERSION,
            USER_ID,
            BILLING_ENTRY_GROUP_NAME,
            BILLING_ENTRY_GROUP_TYPE
        )
    }

    private fun createBillingEntryHttpRequest(): BillingEntryHttpRequest {
        return BillingEntryHttpRequest(
            BILLING_ENTRY_NAME,
            BILLING_ENTRY_AMOUNT,
            BILLING_ENTRY_GROUP_ID
        )
    }

    private fun createBillingEntryHttpResponse(): BillingEntryHttpResponse {
        return BillingEntryHttpResponse(
            BILLING_ENTRY_ID,
            BILLING_ENTRY_CREATED_TIMESTAMP,
            BILLING_ENTRY_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_VERSION,
            USER_ID,
            BILLING_ENTRY_NAME,
            BILLING_ENTRY_AMOUNT,
            createBillingEntryGroupHttpResponse()
        )
    }

    private fun createBillingEntryGroupHttpResponse(): BillingEntryGroupHttpResponse {
        return BillingEntryGroupHttpResponse(
            BILLING_ENTRY_GROUP_ID,
            BILLING_ENTRY_GROUP_CREATED_TIMESTAMP,
            BILLING_ENTRY_GROUP_LAST_UPDATE_TIMESTAMP,
            BILLING_ENTRY_GROUP_VERSION,
            USER_ID,
            BILLING_ENTRY_GROUP_NAME,
            BILLING_ENTRY_GROUP_TYPE
        )
    }
}