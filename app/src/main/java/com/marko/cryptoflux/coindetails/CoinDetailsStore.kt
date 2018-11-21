package com.marko.cryptoflux.coindetails

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.actions.GetCoinAction
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.injection.activity.ActivityScope
import com.marko.cryptoflux.store.Store
import com.marko.cryptoflux.usecases.GetCoin
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class CoinDetailsStore @Inject constructor(
	coroutineDispatchers: CoroutineDispatchers,
	dispatcher: Dispatcher,
	private val getCoin: GetCoin
) : Store<Result<Coin>>(coroutineDispatchers, dispatcher) {

	override fun handleAction(action: Action) {
		when (action) {
			is GetCoinAction -> launch { _state.value = getCoin(action.id) }
		}
	}
}