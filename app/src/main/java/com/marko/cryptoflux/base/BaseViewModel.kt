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

abstract class BaseViewModel<T>(
	dispatcher: Dispatcher,
	private val dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope {

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

	private val subscription = dispatcher.events.openSubscription()

	protected val _state = MutableLiveData<T>()
	val state: LiveData<T>
		get() = _state.distinct()

	init {
		launch { subscription.consumeEach(::handleActions) }
	}

	/**
	 * Cancel [job] and cancel [subscription]
	 */
	override fun onCleared() {
		super.onCleared()

		job.cancel()
		subscription.cancel()
	}
}