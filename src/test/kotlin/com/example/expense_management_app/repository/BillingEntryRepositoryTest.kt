package com.example.expense_management_app.repository

import com.example.expense_management_app.configuration.TestContainersInitializer
import com.example.expense_management_app.model.BillingEntry
import com.example.expense_management_app.model.BillingEntryGroup
import com.example.expense_management_app.model.BillingEntryType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
@ExtendWith(TestContainersInitializer::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = [TestContainersInitializer::class])
class BillingEntryRepositoryTest {

    companion object {
        private const val BILLING_ENTRY_NAME = "billing_entry test"
        private val BILLING_ENTRY_AMOUNT = BigDecimal("12345678910123456789123123123123123")
        private const val BILLING_ENTRY_GROUP_NAME = "billing_entry_group test"
    }

    @Autowired
    private lateinit var billingEntryRepository: BillingEntryRepository

    @Test
    fun whenAuditablePropertiesAreNullThenJpaShouldInitializeItDuringSave() {
        val result = billingEntryRepository.save(
            BillingEntry(
                null, null, null, null,
                BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, createBillingEntryGroup()
            )
        )

        assertNotNull(result.id)
        assertNotNull(result.createdTimestamp)
        assertNotNull(result.lastUpdatedTimestamp)
        assertNotNull(result.version)
        assertEquals(0, result.version)

        assertNotNull(result.billingEntryGroup.id)
        assertNotNull(result.billingEntryGroup.createdTimestamp)
        assertNotNull(result.billingEntryGroup.version)
        assertNotNull(result.billingEntryGroup.lastUpdatedTimestamp)
        assertEquals(0, result.billingEntryGroup.version)
    }

    @Test
    fun whenUpdateEntityThenLastUpdateDateShouldBeInitializedAndVersionIncreased() {
        val savedEntity = billingEntryRepository.save(
            BillingEntry(
                null, null, null, null,
                BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, createBillingEntryGroup()
            )
        )

        assertNotNull(savedEntity.version)
        assertEquals(0, savedEntity.version)

        savedEntity.name = "updated"
        val result = billingEntryRepository.saveAndFlush(savedEntity)
        assertNotNull(result.lastUpdatedTimestamp)
        assertEquals(1, result.version)
        assertEquals("updated", result.name)
    }

    @Test
    fun shouldSaveBillingEntryWithGroup() {
        val billingEntryToSave = BillingEntry(
            null, null, null, null,
            BILLING_ENTRY_NAME, BILLING_ENTRY_AMOUNT, createBillingEntryGroup()
        )

        val result = billingEntryRepository.save(billingEntryToSave)

        assertEquals(BILLING_ENTRY_NAME, result.name)
        assertEquals(BILLING_ENTRY_AMOUNT, result.amount)
        assertNotNull(result.billingEntryGroup)
        assertEquals(BILLING_ENTRY_GROUP_NAME, result.billingEntryGroup.name)
        assertEquals(BillingEntryType.PROFIT, result.billingEntryGroup.type)
    }

    private fun createBillingEntryGroup(): BillingEntryGroup {
        return BillingEntryGroup(null, null, null, null, BILLING_ENTRY_GROUP_NAME, BillingEntryType.PROFIT)
    }
}