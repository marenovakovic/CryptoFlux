package com.marko.cryptoflux.repository

import com.marko.cryptoflux.common.TestUtils
import com.marko.cryptoflux.entities.CoinsResponse
import com.marko.cryptoflux.retrofit.CoinsApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoinsRepositoryImplTest {

	private val api = mock<CoinsApi>()
	private val coinsRepository = CoinsRepositoryImpl(api)

	@Test
	fun `test getCoins`() = runBlocking<Unit> {
		val response = TestUtils.loadGson("mock/get_coins.json", CoinsResponse::class.java)
		val coins = response.data

		stubResponse(response)

		assert(coinsRepository.getCoins() == coins)
	}

	private fun stubResponse(response: CoinsResponse) = runBlocking {
		whenever(api.coinsService.getCoins().await())
			.thenReturn(response)
	}
}