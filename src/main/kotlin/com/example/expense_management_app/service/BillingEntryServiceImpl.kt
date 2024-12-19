package com.example.expense_management_app.service

import com.example.expense_management_app.exception.ResourceNotFoundException
import com.example.expense_management_app.mapper.BillingEntryMapper
import com.example.expense_management_app.model.dto.BillingEntryDto
import com.example.expense_management_app.model.http.BillingEntryHttpRequest
import com.example.expense_management_app.repository.BillingEntryGroupRepository
import com.example.expense_management_app.repository.BillingEntryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BillingEntryServiceImpl(
    private val billingEntryRepository: BillingEntryRepository,
    private val billingEntryGroupRepository: BillingEntryGroupRepository,
) : BillingEntryService {

    override fun save(request: BillingEntryHttpRequest): BillingEntryDto {
        if (!billingEntryGroupRepository.existsById(request.billingEntryGroupId)) {
            throw ResourceNotFoundException("BillingEntryGroup not found by id: ${request.billingEntryGroupId}")
        }

        val entity = BillingEntryMapper.toEntity(BillingEntryMapper.toDto(request))
        entity.billingEntryGroup = billingEntryGroupRepository.findById(request.billingEntryGroupId).get()
        val savedEntity = billingEntryRepository.save(entity)
        return BillingEntryMapper.toDto(savedEntity)
    }

    override fun update(id: UUID, request: BillingEntryHttpRequest): BillingEntryDto {
        if (!billingEntryRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntry not found by id: $id")
        }
        if (!billingEntryGroupRepository.existsById(request.billingEntryGroupId)) {
            throw ResourceNotFoundException("BillingEntryGroup not found by id: ${request.billingEntryGroupId}")
        }

        val billingEntryGroupEntity = billingEntryGroupRepository.findById(request.billingEntryGroupId).get()
        val entity = billingEntryRepository.findById(id).get().copy(
            name = request.name,
            amount = request.amount,
            billingEntryGroup = billingEntryGroupEntity
        )
        val savedEntity = billingEntryRepository.save(entity)
        return BillingEntryMapper.toDto(savedEntity)
    }

    override fun deleteById(id: UUID) {
        if (!billingEntryRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntry not found by id: $id")
        }
        billingEntryRepository.deleteById(id)
    }

    override fun findAll(pageable: Pageable): Page<BillingEntryDto> {
        return billingEntryRepository.findAll(pageable).map {
            BillingEntryMapper.toDto(it)
        }
    }

    override fun findById(id: UUID): BillingEntryDto {
        if (!billingEntryRepository.existsById(id)) {
            throw ResourceNotFoundException("BillingEntry not found by id: $id")
        }
        return BillingEntryMapper.toDto(billingEntryRepository.findById(id).get())
    }
}