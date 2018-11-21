package com.marko.cryptoflux.usecases

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.common.CoroutineDispatchersTest
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.factory.Factory
import com.marko.cryptoflux.repository.CoinsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCoinsTest {

	private val dispatchers = CoroutineDispatchersTest()
	private val coinsRepository = mock<CoinsRepository>()
	private val getCoins = GetCoins(dispatchers, coinsRepository)

	@Test
	fun `does use case calls repository`() = runBlocking<Unit> {
		getCoins()
		verify(coinsRepository).getCoins()
	}

	@Test
	fun `check use case result`() = runBlocking<Unit> {
		val coins = Factory.coins
		stubCoins(coins)

		val result = (getCoins() as Result.Success).data
		assert(result == coins)
	}

	private fun stubCoins(coins: List<Coin>) = runBlocking {
		whenever(coinsRepository.getCoins())
			.thenReturn(coins)
	}
}