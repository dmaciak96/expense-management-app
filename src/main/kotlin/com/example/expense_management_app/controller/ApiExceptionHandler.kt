package com.example.expense_management_app.controller

import com.example.expense_management_app.exception.ResourceNotFoundException
import com.example.expense_management_app.model.http.ErrorHttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import java.time.Instant

@ControllerAdvice(annotations = [RestController::class])
class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorHttpResponse> {
        val errorHttpResponse = exception.message?.let { ErrorHttpResponse(it, Instant.now()) }
        return ResponseEntity<ErrorHttpResponse>(errorHttpResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(
        exception: Throwable,
        request: WebRequest
    ): ResponseEntity<ErrorHttpResponse> {
        val errorHttpResponse = exception.message?.let { ErrorHttpResponse(it, Instant.now()) }
        return ResponseEntity<ErrorHttpResponse>(errorHttpResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}