package com.example.expense_management_app.repository

import com.example.expense_management_app.model.Expense
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExpenseRepository : JpaRepository<Expense, UUID> {
}