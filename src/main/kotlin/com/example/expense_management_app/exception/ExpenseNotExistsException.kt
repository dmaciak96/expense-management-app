package com.example.expense_management_app.exception

import java.util.*

class ExpenseNotExistsException(id: UUID) :
    RuntimeException("Expense not exists with the following id: $id")