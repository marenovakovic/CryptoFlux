package com.marko.cryptoflux.home

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.usecases.GetCoins
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

	@Provides
	fun provideCoinsViewModelFactory(
		dispatcher: Dispatcher,
		dispatchers: CoroutineDispatchers,
		getCoins: GetCoins
	): CoinsViewModelFactory = CoinsViewModelFactory(dispatcher, dispatchers, getCoins)
}