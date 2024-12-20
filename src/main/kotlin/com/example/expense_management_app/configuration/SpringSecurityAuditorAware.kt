package com.example.expense_management_app.configuration

import com.example.expense_management_app.repository.AppUserRepository
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SpringSecurityAuditorAware(private val appUserRepository: AppUserRepository) : AuditorAware<UUID> {
    override fun getCurrentAuditor(): Optional<UUID> {
        val userEmail = Optional.ofNullable(SecurityContextHolder.getContext())
            .map { it.authentication }
            .filter { it.isAuthenticated }
            .map { it.principal.toString() }
        if (userEmail.isPresent && appUserRepository.existsByEmail(userEmail.get())) {
            return Optional.ofNullable(appUserRepository.findByEmail(userEmail.get()).get().id)
        }
        return Optional.empty()
    }
}