package com.example.expense_management_app.repository

import com.example.expense_management_app.model.BillingEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BillingEntryRepository : JpaRepository<BillingEntry, UUID>