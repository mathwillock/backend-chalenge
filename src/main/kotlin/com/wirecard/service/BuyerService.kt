package com.wirecard.service

import com.wirecard.model.Buyer
import com.wirecard.repository.BuyerRepository
import jakarta.inject.Singleton

@Singleton
class BuyerService(
    private val buyerRepository: BuyerRepository
) {

    fun createBuyer(buyer: Buyer): Buyer {
        if (findBuyer(buyer.cpf)) {
             return buyerRepository.save(buyer)
        }

        throw IllegalArgumentException("Buyer already exists")
    }

    fun updateBuyer(buyer: Buyer): Buyer {
         buyerRepository.findByCpf(buyer.cpf)?.let {
            it.name = buyer.name
            it.email = buyer.email
            return buyerRepository.update(it)
         }

        throw IllegalArgumentException("Buyer does not exists")
    }

    private fun findBuyer(cpf: String): Boolean {
        buyerRepository.findByCpf(cpf)?.let {
            return false
        }

       return true
    }




}
