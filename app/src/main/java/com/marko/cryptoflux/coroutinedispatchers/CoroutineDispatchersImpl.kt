package com.marko.cryptoflux.coroutinedispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [CoroutineDispatchers] implementation
 */
@Singleton
class CoroutineDispatchersImpl @Inject constructor(): CoroutineDispatchers {

	override val main: CoroutineDispatcher
		get() = Dispatchers.Main

	override val io: CoroutineDispatcher
		get() = Dispatchers.IO

	override val background: CoroutineDispatcher
		get() = Dispatchers.Default
}