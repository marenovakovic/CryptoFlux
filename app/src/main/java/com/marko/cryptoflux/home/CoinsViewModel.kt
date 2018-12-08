package com.marko.cryptoflux.home

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.actions.GetCoinsAction
import com.marko.cryptoflux.base.BaseViewModel
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.usecases.GetCoins
import com.marko.cryptoflux.usecases.invoke
import kotlinx.coroutines.launch

class CoinsViewModel(
	dispatcher: Dispatcher,
	dispatchers: CoroutineDispatchers,
	private val getCoins: GetCoins
) : BaseViewModel<Result<List<Coin>>>(dispatcher, dispatchers) {

	override fun handleActions(action: Action) {
		when (action) {
			is GetCoinsAction -> launch { _state.value = getCoins() }
		}
	}
}