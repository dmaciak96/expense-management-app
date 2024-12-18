package com.example.expense_management_app.service

import com.example.expense_management_app.exception.ResourceNotFoundException
import com.example.expense_management_app.mapper.BillingEntryGroupMapper
import com.example.expense_management_app.model.dto.BillingEntryGroupDto
import com.example.expense_management_app.model.http.BillingEntryGroupHttpRequest
import com.example.expense_management_app.repository.BillingEntryGroupRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BillingEntryGroupServiceImpl(
    val billingEntryGroupRepository: BillingEntryGroupRepository
) : BillingEntryGroupService {

    override fun save(request: BillingEntryGroupHttpRequest): BillingEntryGroupDto {
        val entity = BillingEntryGroupMapper.toEntity(BillingEntryGroupMapper.toDto(request))
        val savedEntity = billingEntryGroupRepository.save(entity)
        return BillingEntryGroupMapper.toDto(savedEntity)
    }

    override fun update(id: UUID, request: BillingEntryGroupHttpRequest): BillingEntryGroupDto {
        if (!billingEntryGroupRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntryGroup not found by id: $id")
        }
        val entityToSave = billingEntryGroupRepository.findById(id).get().copy(name = request.name, type = request.type)
        val savedEntity = billingEntryGroupRepository.save(entityToSave)
        return BillingEntryGroupMapper.toDto(savedEntity)
    }

    override fun deleteById(id: UUID) {
        if (!billingEntryGroupRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntryGroup not found by id: $id")
        }
        billingEntryGroupRepository.deleteById(id)
    }

    override fun findAll(pageable: Pageable): Page<BillingEntryGroupDto> {
        return billingEntryGroupRepository.findAll(pageable).map {
            BillingEntryGroupMapper.toDto(it)
        }
    }

    override fun findById(id: UUID): BillingEntryGroupDto {
        if (!billingEntryGroupRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntryGroup not found by id: $id")
        }
        val entity = billingEntryGroupRepository.findById(id).get()
        return BillingEntryGroupMapper.toDto(entity)
    }
}