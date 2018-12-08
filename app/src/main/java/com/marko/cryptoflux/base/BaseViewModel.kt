package com.marko.cryptoflux.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.extensions.distinct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Base [ViewModel] class with [CoroutineScope]
 *
 * @param dispatcher [Dispatcher] from where [BaseViewModel] will get instructions
 *
 * @param dispatchers [CoroutineDispatchers]
 */
abstract class BaseViewModel<T>(
	dispatcher: Dispatcher,
	private val dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope {

	/**
	 * Handle actions dispatched by [Dispatcher]
	 */
	protected abstract fun handleActions(action: Action)

	/**
	 * Parent [Job]
	 */
	private val job = Job()

	/**
	 * [ViewModel] [CoroutineContext] [Dispatchers.Main]
	 */
	override val coroutineContext: CoroutineContext
		get() = dispatchers.main + job

	/**
	 * Open subscription to [Dispatcher.events]
	 */
	private val subscription = dispatcher.events.openSubscription()

	/**
	 * State view will react to
	 */
	private  val _state = MutableLiveData<T>()
	val state: LiveData<T>
		get() = _state.distinct()

	/**
	 * Start listening to [Dispatcher.events]
	 */
	init {
		launch { subscription.consumeEach(::handleActions) }
	}

	/**
	 * Sets the state so [_state] doesn't have to be exposed
	 *
	 * @param value state to be set
	 */
	protected fun setState(value: T) { _state.value = value }

	/**
	 * Cancel [job] and cancel [subscription]
	 */
	override fun onCleared() {
		super.onCleared()

		job.cancel()
		subscription.cancel()
	}
}