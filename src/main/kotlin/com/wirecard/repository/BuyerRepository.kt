package com.wirecard.repository

import com.wirecard.model.Buyer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BuyerRepository : CrudRepository<Buyer, Long> {
    fun findByCpf(cpf: String): Buyer?
}