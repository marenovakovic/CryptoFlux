package com.marko.cryptoflux.repository

import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinId

/**
 * Repository that handles fetching of [Coin]s
 */
interface CoinsRepository {

	/**
	 * Fetch all [Coin]s
	 */
	suspend fun getCoins(): List<Coin>

	/**
	 * Fetch single [Coin]
	 * @param id [CoinId] id of coin that should be fetched
	 */
	suspend fun getCoin(id: CoinId): Coin
}