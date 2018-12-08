package com.marko.cryptoflux.common

import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestCoroutineDispatchers : CoroutineDispatchers {

	override val main: CoroutineDispatcher
		get() = Dispatchers.Unconfined

	override val io: CoroutineDispatcher
		get() = Dispatchers.Unconfined

	override val background: CoroutineDispatcher
		get() = Dispatchers.Unconfined
}