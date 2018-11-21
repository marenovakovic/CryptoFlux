package com.marko.cryptoflux.dispatcher

import com.marko.cryptoflux.actions.GetCoinsAction
import com.marko.cryptoflux.actions.SaveCoinsAction
import com.marko.cryptoflux.common.BaseTest
import com.marko.cryptoflux.common.CoroutineDispatchersTest
import com.marko.cryptoflux.factory.Factory
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DispatcherTest : BaseTest() {

	private val dispatchers = CoroutineDispatchersTest()
	private val dispatcher = DispatcherImpl(dispatchers)

	@Test
	fun `test dispatch GetCoins action`() = runBlocking<Unit> {
		dispatcher.dispatch(GetCoinsAction)

		val action = dispatcher.events.receive()
		assert(action === GetCoinsAction)
		dispatcher.events.cancel()
	}

	@Test
	fun `test SaveCoins action`() = runBlocking<Unit> {
		dispatcher.dispatch(SaveCoinsAction(listOf()))

		val action = dispatcher.events.receive()
		assert(action is SaveCoinsAction)
		dispatcher.events.cancel()
	}

	@Test
	fun `test SaveCoins action value`() = runBlocking<Unit> {
		val coins = Factory.coins
		dispatcher.dispatch(SaveCoinsAction(coins))

		val action = dispatcher.events.receive()
		assert(action is SaveCoinsAction)
		assert((action as SaveCoinsAction).coins == coins)
		dispatcher.events.cancel()
	}
}