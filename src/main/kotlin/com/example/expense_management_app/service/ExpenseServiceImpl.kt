package com.example.expense_management_app.service

import com.example.expense_management_app.exception.ExpenseNotExistsException
import com.example.expense_management_app.model.Expense
import com.example.expense_management_app.repository.ExpenseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseServiceImpl(
    private val expenseRepository: ExpenseRepository
) : ExpenseService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ExpenseServiceImpl::class.java)
    }

    override fun add(expense: Expense): Expense {
        log.info("Creating new expense ${expense.name}")
        return expenseRepository.save(expense)
    }

    override fun findAll(pageable: Pageable): Page<Expense> {
        log.info("Fetch ${pageable.pageNumber} page of expenses (page size: ${pageable.pageSize})")
        return expenseRepository.findAll(pageable)
    }

    override fun findById(id: UUID): Optional<Expense> {
        if (!expenseRepository.existsById(id)) {
            throw ExpenseNotExistsException(id)
        }
        log.debug("Try to find expense by id {}", id)
        return expenseRepository.findById(id)
    }

    override fun update(id: UUID, expense: Expense): Expense {
        if (!expenseRepository.existsById(id)) {
            throw ExpenseNotExistsException(id)
        }
        log.info("Updating expense ${expense.name}")
        return expenseRepository.save(expense)
    }

    override fun delete(id: UUID) {
        if (!expenseRepository.existsById(id)) {
            throw ExpenseNotExistsException(id)
        }
        log.debug("Deleting expense by id {}", id)
        expenseRepository.deleteById(id)
    }
}