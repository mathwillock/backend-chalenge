package com.wirecard.service

import com.wirecard.model.Buyer
import com.wirecard.repository.BuyerRepository
import jakarta.inject.Singleton

@Singleton
class BuyerService(
    private val buyerRepository: BuyerRepository
) {

    fun createBuyer(buyer: Buyer): Buyer  {

        if (buyerExistent(buyer.cpf)) {
            return buyerRepository.save(buyer)
        }
        throw IllegalArgumentException("Buyer already exists")

//        throw Exception("Buyer already exists")
    }


    fun buyerExistent(cpf: String): Boolean {
        val getBuyer = buyerRepository.findByCpf(cpf)
        if (getBuyer != null) {
            return false
        }
       return true
    }

}
