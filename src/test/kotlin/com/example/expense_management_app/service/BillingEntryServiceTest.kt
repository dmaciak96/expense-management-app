package com.example.expense_management_app.service

import com.example.expense_management_app.exception.ResourceNotFoundException
import com.example.expense_management_app.model.BillingEntryType
import com.example.expense_management_app.model.entity.BillingEntry
import com.example.expense_management_app.model.entity.BillingEntryGroup
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.repository.BillingEntryGroupRepository
import com.example.expense_management_app.repository.BillingEntryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.Instant
import java.util.*
import kotlin.test.assertNotNull

class BillingEntryServiceTest {

    private val billingEntryRepository = mock(BillingEntryRepository::class.java)
    private val billingEntryGroupRepository: BillingEntryGroupRepository = mock(BillingEntryGroupRepository::class.java)
    private val billingEntryService = BillingEntryServiceImpl(billingEntryRepository, billingEntryGroupRepository)

    companion object {
        private val BILLING_ENTRY_GROUP_ID = UUID.randomUUID()
        private val BILLING_ENTRY_GROUP_NAME = "billing entry group name"
        private val BILLING_ENTRY_GROUP_TYPE = BillingEntryType.PROFIT
        private val BILLING_ENTRY_ID = UUID.randomUUID()
        private val BILLING_ENTRY_NAME = "billing entry name"
        private val BILLING_ENTRY_AMOUNT = BigDecimal("123123123.123123123123123")
        private val CREATED_TIMESTAMP = Instant.now()
        private val UPDATED_TIMESTAMP = Instant.now()
        private val VERSION = 1
        private val USER_ID = UUID.randomUUID()
    }

    @BeforeEach
    fun setupMocks() {
        Mockito.`when`(billingEntryGroupRepository.existsById(any())).thenReturn(false)
        Mockito.`when`(billingEntryGroupRepository.existsById(BILLING_ENTRY_GROUP_ID)).thenReturn(true)
        Mockito.`when`(billingEntryGroupRepository.findById(BILLING_ENTRY_GROUP_ID))
            .thenReturn(Optional.ofNullable(createBillingGroup()))
        Mockito.`when`(billingEntryRepository.save(any(BillingEntry::class.java))).thenAnswer {
            it.arguments[0]
        }
        Mockito.`when`(billingEntryRepository.existsById(any())).thenReturn(false)
        Mockito.`when`(billingEntryRepository.existsById(BILLING_ENTRY_ID)).thenReturn(true)
        Mockito.`when`(billingEntryRepository.findById(BILLING_ENTRY_ID))
            .thenReturn(Optional.ofNullable(createBillingEntry()))
    }

    @Test
    fun shouldThrowExceptionIfBillingGroupNotExists() {
        val groupId = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntryGroup not found by id: $groupId") {
            billingEntryService.save(BillingEntryHttpRequest(BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, groupId))
        }
    }

    @Test
    fun shouldSaveBillingEntry() {
        val result = billingEntryService.save(
            BillingEntryHttpRequest(
                BILLING_ENTRY_NAME,
                BILLING_ENTRY_AMOUNT,
                BILLING_ENTRY_GROUP_ID
            )
        )
        assertNotNull(result)
        verify(billingEntryRepository).save(any(BillingEntry::class.java))
    }

    @Test
    fun shouldThrowExceptionIfBillingGroupNotFoundDuringUpdate() {
        val groupId = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntryGroup not found by id: $groupId") {
            billingEntryService.update(
                BILLING_ENTRY_ID,
                BillingEntryHttpRequest(BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, groupId)
            )
        }
    }

    @Test
    fun shouldThrowExceptionIfBillingEntryNotFoundDuringUpdate() {
        val billingEntryId = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntry not found by id: $billingEntryId") {
            billingEntryService.update(
                billingEntryId,
                BillingEntryHttpRequest(BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, BILLING_ENTRY_GROUP_ID)
            )
        }
    }

    @Test
    fun shouldUpdateExistingBillingEntry() {
        val result = billingEntryService.update(
            BILLING_ENTRY_ID,
            BillingEntryHttpRequest(BILLING_ENTRY_NAME + "UPDATE", BILLING_ENTRY_AMOUNT, BILLING_ENTRY_GROUP_ID)
        )
        assertNotNull(result)
        verify(billingEntryRepository).save(any(BillingEntry::class.java))
    }

    @Test
    fun shouldThrowExceptionIfBillingEntryNotExistsDuringDelete() {
        val billingEntryId = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntry not found by id: $billingEntryId") {
            billingEntryService.deleteById(billingEntryId)
        }
    }

    @Test
    fun shouldRunDeleteMethodFromRepository() {
        billingEntryService.deleteById(BILLING_ENTRY_ID)
        verify(billingEntryRepository).deleteById(BILLING_ENTRY_ID)
    }

    @Test
    fun shouldThrowExceptionWhenBillingEntryNotExistsDuringFetchById() {
        val billingEntryId = UUID.randomUUID()
        assertThrows<ResourceNotFoundException>("BillingEntry not found by id: $billingEntryId") {
            billingEntryService.findById(billingEntryId)
        }
    }

    private fun createBillingGroup(): BillingEntryGroup {
        return BillingEntryGroup(
            BILLING_ENTRY_GROUP_ID,
            CREATED_TIMESTAMP,
            UPDATED_TIMESTAMP,
            VERSION,
            USER_ID,
            BILLING_ENTRY_GROUP_NAME,
            BILLING_ENTRY_GROUP_TYPE
        )
    }

    private fun createBillingEntry(): BillingEntry {
        return BillingEntry(
            BILLING_ENTRY_ID,
            CREATED_TIMESTAMP,
            UPDATED_TIMESTAMP,
            VERSION,
            USER_ID,
            BILLING_ENTRY_NAME,
            BILLING_ENTRY_AMOUNT,
            createBillingGroup()
        )
    }
}