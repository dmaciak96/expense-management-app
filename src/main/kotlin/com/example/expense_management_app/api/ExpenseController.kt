package com.example.expense_management_app.api

import com.example.expense_management_app.model.Expense
import com.example.expense_management_app.service.ExpenseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/expenses")
class ExpenseController(private val expenseService: ExpenseService) {

    @GetMapping
    fun findAll(@RequestParam("pageNumber") pageNumber: Int, @RequestParam("pageSize") pageSize: Int): Page<Expense> {
        return expenseService.findAll(PageRequest.of(pageNumber, pageSize))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID): Optional<Expense> {
        return expenseService.findById(id)
    }

    @PostMapping
    fun save(@RequestBody expense: Expense): Expense {
        return expenseService.add(expense)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: UUID, @RequestBody expense: Expense): Expense {
        return expenseService.update(id, expense)
    }
}