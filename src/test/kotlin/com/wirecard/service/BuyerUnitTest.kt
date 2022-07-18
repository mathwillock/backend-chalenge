package com.wirecard.service

import com.wirecard.model.Buyer
import com.wirecard.repository.BuyerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BuyerUnitTest {

    @InjectMockKs
    lateinit var buyerService: BuyerService

    @MockK
    lateinit var buyerRepository: BuyerRepository

    @Test
    fun `Given a new buyer should create buyer`() {
        //given
        every { buyerRepository.findByCpf(any()) } returns null
        every { buyerRepository.save(any()) } returns buyer1

        //when
        val createBuyer: Buyer = buyerService.createBuyer(buyer1)

        //then
        assertThat(createBuyer, equalTo(buyer1))

    }

    @Test
    fun `Given new Buyer with already used cpf should return exception`() {
        //given
        every { buyerRepository.findByCpf(any()) } returns buyer1

        //when
        //then
        assertThrows<IllegalArgumentException> { buyerService.createBuyer(buyer1) }

    }

    @Test
    fun `Given an existing buyer with valid cpf must update `() {
        //given
        every { buyerRepository.save(any()) } returns buyer1
        every { buyerRepository.findByCpf(any()) } returns buyer1
        every { buyerRepository.update(any()) } returns buyer2

        //when
        val updateBuyer: Buyer = buyerService.updateBuyer(buyer2)

        //then
        assertThat(updateBuyer, equalTo(buyer1))
    }

    @Test
    fun `Given an existing buyer with invalid cpf must return exception`() {
        //given
        every { buyerRepository.save(any()) } returns buyer1
        every { buyerRepository.findByCpf(any()) } returns null
        every { buyerRepository.update(any()) } returns buyer2

        //when
        assertThrows<IllegalArgumentException> { buyerService.updateBuyer(buyer2) }
    }

    private var buyer1 = Buyer(
        1L, "John Doe", "Doe@email.com", "123456789"
    )

    private var buyer2 = Buyer(
        1L, "John Doe 2", "Doe2@email.com", "123456789"
    )
}