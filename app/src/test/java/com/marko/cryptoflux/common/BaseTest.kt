package com.marko.cryptoflux.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseTest : CoroutineScope {

	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.Unconfined + job
}