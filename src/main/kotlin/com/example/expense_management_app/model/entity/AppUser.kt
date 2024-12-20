package com.example.expense_management_app.model.entity

import com.example.expense_management_app.model.AppUserType
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class AppUser(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @CreatedDate
    var createdTimestamp: Instant?,

    @LastModifiedDate
    var lastUpdatedTimestamp: Instant?,

    @Version
    var version: Int?,

    var email: String,

    var password: String,

    var firstName: String,

    var lastName: String,

    var enabled: Boolean,

    @Enumerated(value = EnumType.STRING)
    var userType: AppUserType,

    @Lob
    var photo: ByteArray?
)
