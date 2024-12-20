package com.example.expense_management_app.repository

import com.example.expense_management_app.model.entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppUserRepository : JpaRepository<AppUser, UUID> {
    fun findByEmail(email: String): Optional<AppUser>
    fun existsByEmail(email: String): Boolean
}