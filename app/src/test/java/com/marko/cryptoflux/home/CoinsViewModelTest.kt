package com.marko.cryptoflux.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.common.TestCoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.repository.CoinsRepository
import com.marko.cryptoflux.usecases.GetCoins
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	private val dispatcher = mockk<Dispatcher>()
	private val dispatchers = TestCoroutineDispatchers()
	private val repository = mockk<CoinsRepository>()
	private val getCoins = GetCoins(dispatchers, repository)

	private val viewModel = CoinsViewModel(dispatcher, dispatchers, getCoins)

	@Test
	fun nothing() = runBlocking {
		val events = ConflatedBroadcastChannel<Action>()
		stubEvents(events)

		Unit
	}

	private fun stubEvents(events: BroadcastChannel<Action>) = runBlocking {
		coEvery { dispatcher.events } returns events
	}
}