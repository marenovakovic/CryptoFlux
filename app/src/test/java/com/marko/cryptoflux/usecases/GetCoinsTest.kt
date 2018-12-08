package com.marko.cryptoflux.usecases

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.common.TestCoroutineDispatchers
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.factory.Factory
import com.marko.cryptoflux.repository.CoinsRepository
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCoinsTest {

	private val coinsRepository: CoinsRepository = mockk()
	private val dispatchers = TestCoroutineDispatchers()
	private val getCoins = GetCoins(dispatchers, coinsRepository)

	@Test
	fun `test does use case calls repository`() = runBlocking {
		stubCoins(Factory.coins)
		getCoins()
		coVerify(exactly = 1) { coinsRepository.getCoins() }
	}

	@Test
	fun `check use case result`() = runBlocking {
		val coins = Factory.coins
		val result = getCoins()

		println((result as Result.Error).exception)

		assert(result is Result.Success)
		assertEquals((result as Result.Success).data, coins)
	}

	private fun stubCoins(coins: List<Coin>) {
		coEvery { coinsRepository.getCoins() } returns coins
	}
}