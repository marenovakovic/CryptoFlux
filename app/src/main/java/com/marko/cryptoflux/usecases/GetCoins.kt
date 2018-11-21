package com.marko.cryptoflux.usecases

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.repository.CoinsRepository
import javax.inject.Inject

/**
 * Handles fetching [Coin]s. Interacts with [CoinsRepository]
 *
 * @param dispatchers [CoroutineDispatchers]
 *
 * @param coinsRepository [CoinsRepository] use case call this repository [CoinsRepository.getCoins]
 */
class GetCoins @Inject constructor(
	dispatchers: CoroutineDispatchers,
	private val coinsRepository: CoinsRepository
) : UseCase<Unit, List<Coin>>(dispatchers) {

	/**
	 * Executes logic for fetching [Coin]s
	 */
	override suspend fun execute(parameters: Unit): List<Coin> = coinsRepository.getCoins()
}