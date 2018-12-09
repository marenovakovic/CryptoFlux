package com.marko.cryptoflux.coindetails

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.actions.GetCoinAction
import com.marko.cryptoflux.base.BaseViewModel
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.usecases.GetCoin
import kotlinx.coroutines.launch

class CoinDetailsViewModel(
	dispatcher: Dispatcher,
	dispatchers: CoroutineDispatchers,
	private val getCoin: GetCoin
) : BaseViewModel<Result<Coin>>(dispatcher, dispatchers) {

	override fun handleActions(action: Action) {
		when(action) {
			is GetCoinAction -> launch { setState(getCoin(action.id)) }
		}
	}
}