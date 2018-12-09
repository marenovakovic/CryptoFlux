package com.marko.cryptoflux.coindetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.usecases.GetCoin

class CoinDetailsViewModelFactory(
	private val dispatcher: Dispatcher,
	private val dispatchers: CoroutineDispatchers,
	private val getCoin: GetCoin
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		CoinDetailsViewModel(
			dispatcher = dispatcher,
			dispatchers = dispatchers,
			getCoin = getCoin
		) as T
}