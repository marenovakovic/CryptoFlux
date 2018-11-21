package com.marko.cryptoflux.usecases

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinId
import com.marko.cryptoflux.repository.CoinsRepository
import javax.inject.Inject

/**
 * Handles fetching of [Coin]. Interacts with [CoinsRepository]
 *
 * @param dispatchers [CoroutineDispatchers]
 *
 * @param coinsRepository [CoinsRepository] use case call this repository [CoinsRepository.getCoin]
 */
class GetCoin @Inject constructor(
	dispatchers: CoroutineDispatchers,
	private val coinsRepository: CoinsRepository
) : UseCase<CoinId, Coin>(dispatchers) {

	override suspend fun execute(parameters: CoinId): Coin =
		coinsRepository.getCoin(parameters)
}