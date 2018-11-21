package com.marko.cryptoflux.dispatcher

import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Receives [Action] and dispatches it to the stores
 *
 * @param coroutineDispatchers abstracted [Dispatchers] for easy testing
 */
@Singleton
class DispatcherImpl @Inject constructor(
	coroutineDispatchers: CoroutineDispatchers
) : BaseDispatcher(coroutineDispatchers), Dispatcher {

	/**
	 * [Action] events that stores subscribe to
	 */
	private val _events = ConflatedBroadcastChannel<Action>()
	override val events: ReceiveChannel<Action> = _events.openSubscription()

	/**
	 * Adding [Action] to the events
	 *
	 * @param action to be added to events
	 */
	override fun dispatch(action: Action) {
		launch {
			_events.offer(action)
		}
	}
}