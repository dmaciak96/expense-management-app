package com.example.expense_management_app.service

import com.example.expense_management_app.model.Expense
import java.util.*

interface ExpenseService {
    fun add(expense: Expense): Expense
    fun findAll(): List<Expense>
    fun findById(id: UUID): Expense
    fun update(expense: Expense): Expense
    fun delete(id: UUID)
}