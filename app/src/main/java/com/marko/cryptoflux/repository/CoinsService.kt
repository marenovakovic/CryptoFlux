package com.marko.cryptoflux.repository

import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinDetailsResponse
import com.marko.cryptoflux.entities.CoinId
import com.marko.cryptoflux.entities.CoinsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * [Retrofit] service for fetching [Coin]s and [Coin] related details
 */
interface CoinsService {

	/**
	 * Fetches [Coin]s
	 */
	@GET("listings")
	fun getCoins(): Deferred<CoinsResponse>

	/**
	 * Fetched [Coin]
	 *
	 * @param id [CoinId] id of coin that should be fetched
	 */
	@GET("ticker/{id}/")
	fun getCoin(@Path("id") id: CoinId): Deferred<CoinDetailsResponse>
}