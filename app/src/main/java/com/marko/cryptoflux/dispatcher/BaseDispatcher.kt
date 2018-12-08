package com.marko.cryptoflux.dispatcher

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Provides [CoroutineScope] to [DispatcherImpl]
 * @param dispatchers abstracted [Dispatchers] for easy testing
 */
abstract class BaseDispatcher(
	private val dispatchers: CoroutineDispatchers
) : CoroutineScope {

	/**
	 * Parent [Job]
	 */
	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = dispatchers.main + job
}