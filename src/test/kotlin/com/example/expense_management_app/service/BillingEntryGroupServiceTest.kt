package com.example.expense_management_app.service

import com.example.expense_management_app.exception.ResourceNotFoundException
import com.example.expense_management_app.model.BillingEntryType
import com.example.expense_management_app.model.entity.BillingEntryGroup
import com.example.expense_management_app.model.http.BillingEntryGroupHttpRequest
import com.example.expense_management_app.repository.BillingEntryGroupRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.time.Instant
import java.util.*
import kotlin.test.assertNotNull

class BillingEntryGroupServiceTest {

    private val billingEntryGroupRepository = mock(BillingEntryGroupRepository::class.java)
    private val billingEntryGroupService = BillingEntryGroupServiceImpl(billingEntryGroupRepository)

    companion object {
        private val ID = UUID.randomUUID()
        private val NAME = "billing entry group name"
        private val TYPE = BillingEntryType.PROFIT
        private val CREATED_TIMESTAMP = Instant.now()
        private val UPDATED_TIMESTAMP = Instant.now()
        private val VERSION = 1
    }

    @BeforeEach
    fun setupMocks() {
        Mockito.`when`(billingEntryGroupRepository.save(any())).thenAnswer {
            it.arguments[0]
        }
        Mockito.`when`(billingEntryGroupRepository.existsById(any())).thenReturn(false)
        Mockito.`when`(billingEntryGroupRepository.existsById(ID)).thenReturn(true)
        Mockito.`when`(billingEntryGroupRepository.findById(ID))
            .thenReturn(Optional.ofNullable(createBillingEntryGroup()))
    }

    @Test
    fun shouldSaveBillingEntryGroup() {
        val result = billingEntryGroupService.save(BillingEntryGroupHttpRequest(NAME, TYPE))
        assertNotNull(result)
        verify(billingEntryGroupRepository).save(any())
    }

    @Test
    fun shouldThrowExceptionIfGroupNotExistsDuringUpdate() {
        val id = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntryGroup not found by id: $id") {
            billingEntryGroupService.update(id, BillingEntryGroupHttpRequest(NAME, TYPE))
        }
    }

    @Test
    fun shouldUpdateGroup() {
        val result = billingEntryGroupService.update(ID, BillingEntryGroupHttpRequest(NAME, TYPE))
        assertNotNull(result)
        verify(billingEntryGroupRepository).save(any())
    }

    @Test
    fun shouldThrowExceptionIfGroupNotExistsDuringDelete() {
        val id = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntryGroup not found by id: $id") {
            billingEntryGroupService.deleteById(id)
        }
    }

    @Test
    fun shouldThrowExceptionIfGroupNotExistsDuringFetchById() {
        val id = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntryGroup not found by id: $id") {
            billingEntryGroupService.findById(id)
        }
    }

    private fun createBillingEntryGroup(): BillingEntryGroup {
        return BillingEntryGroup(ID, CREATED_TIMESTAMP, UPDATED_TIMESTAMP, VERSION, NAME, TYPE)
    }
}