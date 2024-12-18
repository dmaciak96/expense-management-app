package com.example.expense_management_app.repository

import com.example.expense_management_app.model.entity.BillingEntryGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BillingEntryGroupRepository : JpaRepository<BillingEntryGroup, UUID>