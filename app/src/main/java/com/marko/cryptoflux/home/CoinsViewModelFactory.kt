package com.marko.cryptoflux.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.usecases.GetCoins

class CoinsViewModelFactory(
	private val dispatcher: Dispatcher,
	private val dispatchers: CoroutineDispatchers,
	private val getCoins: GetCoins
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		CoinsViewModel(
			dispatcher = dispatcher,
			dispatchers = dispatchers,
			getCoins = getCoins
		) as T
}