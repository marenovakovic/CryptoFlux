package com.marko.cryptoflux.dispatcher

import com.marko.cryptoflux.actions.Action
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

interface Dispatcher {

	val events: BroadcastChannel<Action>

	fun dispatch(action: Action)
}