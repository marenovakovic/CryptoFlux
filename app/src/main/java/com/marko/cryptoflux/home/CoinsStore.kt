package com.marko.cryptoflux.home

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.actions.GetCoinsAction
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.dispatcher.DispatcherImpl
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.store.Store
import com.marko.cryptoflux.usecases.GetCoins
import com.marko.cryptoflux.usecases.invoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Store] that handles [Coin] related [Action]s
 *
 * @param coroutineDispatchers [Dispatchers]
 *
 * @param dispatcher [DispatcherImpl] from which store will receive actions to perform
 */
@Singleton
class CoinsStore @Inject constructor(
	coroutineDispatchers: CoroutineDispatchers,
	dispatcher: Dispatcher,
	private val getCoins: GetCoins
) : Store<Result<List<Coin>>>(coroutineDispatchers, dispatcher) {

	override fun handleAction(action: Action) {
		when (action) {
			is GetCoinsAction -> launch { _state.value = getCoins() }
		}
	}
}