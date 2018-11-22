package com.marko.cryptoflux.repository

import com.marko.cryptoflux.common.TestUtils
import com.marko.cryptoflux.entities.CoinsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoinsRepositoryImplTest {

	private val coinsRepository = CoinsRepositoryImpl()

	@Test
	fun `test getCoins`() = runBlocking<Unit> {
		val response = TestUtils.loadGson("mock/get_coins.json", CoinsResponse::class.java)
		val coins = response.data

		stubResponse(response)

		assert(coinsRepository.getCoins() == coins)
	}

	private fun stubResponse(response: CoinsResponse) = runBlocking {
		//		whenever(coins)
//			.thenReturn(response)
	}
}