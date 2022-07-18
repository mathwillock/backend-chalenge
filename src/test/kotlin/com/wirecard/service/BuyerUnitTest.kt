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
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BuyerUnitTest {

    @InjectMockKs
    lateinit var buyerService: BuyerService

    @MockK
    lateinit var buyerRepository: BuyerRepository

    @Test
    fun `given a new buyer should create buyer`() {
        //given
        every { buyerRepository.findByCpf(any()) } returns null
        every { buyerRepository.save(any()) } returns buyer1

        //when
        val createBuyer: Buyer = buyerService.createBuyer(buyer1)

        //then
        assertThat(createBuyer, equalTo(buyer1))

    }

    private var buyer1 = Buyer(
        1L, "John Doe", "Doe@email.com", "123456789"
    )

}