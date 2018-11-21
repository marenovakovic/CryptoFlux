package com.marko.cryptoflux.dispatcher

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Provides [CoroutineScope] to [DispatcherImpl]
 * @param coroutineDispatchers abstracted [Dispatchers] for easy testing
 */
abstract class BaseDispatcher(
	private val coroutineDispatchers: CoroutineDispatchers
) : CoroutineScope {

	/**
	 * Parent [Job]
	 */
	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = coroutineDispatchers.main + job
}