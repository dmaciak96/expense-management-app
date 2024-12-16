package com.example.expense_management_app.service

import com.example.expense_management_app.model.Expense
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ExpenseService {
    fun add(expense: Expense): Expense
    fun findAll(pageable: Pageable): Page<Expense>
    fun findById(id: UUID): Optional<Expense>
    fun update(id: UUID, expense: Expense): Expense
    fun delete(id: UUID)
}