package com.marko.cryptoflux.coindetails

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.usecases.GetCoin
import dagger.Module
import dagger.Provides

@Module
class CoinDetailsModule {

	@Provides
	fun provideCoinDetailsViewModelFactory(
		dispatcher: Dispatcher,
		dispatchers: CoroutineDispatchers,
		getCoin: GetCoin
	): CoinDetailsViewModelFactory = CoinDetailsViewModelFactory(
		dispatcher = dispatcher,
		dispatchers = dispatchers,
		getCoin = getCoin
	)
}