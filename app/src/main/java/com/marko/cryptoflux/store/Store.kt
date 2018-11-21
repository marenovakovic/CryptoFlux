package com.marko.cryptoflux.store

import androidx.lifecycle.*
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.dispatcher.DispatcherImpl
import com.marko.cryptoflux.extensions.distinct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * provides [CoroutineScope] to [Store]s
 *
 * @param coroutineDispatchers [Dispatchers] abstraction
 *
 * @param dispatcher [DispatcherImpl] from which store will receive actions to perform
 */
abstract class Store<T>(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val dispatcher: Dispatcher
) : CoroutineScope, LifecycleObserver {

	/**
	 * Parent [Job]
	 */
	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = coroutineDispatchers.main + job

	/**
	 * Store state. View will react to this state
	 */
	protected val _state = MutableLiveData<T>()
	val state: LiveData<T>
		get() = _state

	/**
	 * Register [LifecycleOwner] for the [Store] and register observer for [Store] state [LiveData]
	 *
	 * @param owner [LifecycleOwner], [Store] will register to and state [LiveData] observer
	 */
	fun observe(owner: LifecycleOwner, onStateChange: (T) -> Unit) {
		owner.lifecycle.addObserver(this)
		_state.distinct().observe(owner, Observer { onStateChange(it) })
	}

	/**
	 * Start to listen for [Dispatcher] events in [Lifecycle.Event.ON_CREATE]
	 */
	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	protected fun subscribe() {
		launch { dispatcher.events.consumeEach(this@Store::handleAction) }
	}

	/**
	 * Stop listening for [Dispatcher] events in [Lifecycle.Event.ON_DESTROY]
	 */
	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun cancel() {
		launch { dispatcher.events.cancel() }
	}

	/**
	 * Handle [Action] dispatched by [Dispatcher]
	 */
	protected abstract fun handleAction(action: Action)
}