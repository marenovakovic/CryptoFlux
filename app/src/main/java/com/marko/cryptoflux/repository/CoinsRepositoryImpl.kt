package com.marko.cryptoflux.repository

import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinId
import com.marko.cryptoflux.retrofit.CoinsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinsRepositoryImpl @Inject constructor() : CoinsRepository {

	override suspend fun getCoins(): List<Coin> {
		val result = CoinsApi.coinsService.getCoins().await()
		return result.data
	}

	override suspend fun getCoin(id: CoinId): Coin {
		val result = CoinsApi.coinsService.getCoin(id = id).await()
		return result.data
	}
}