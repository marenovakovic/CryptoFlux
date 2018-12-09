package com.marko.cryptoflux.coindetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.Action
import com.marko.cryptoflux.actions.GetCoinAction
import com.marko.cryptoflux.actions.GetCoinsAction
import com.marko.cryptoflux.common.TestCoroutineDispatchers
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.factory.Factory
import com.marko.cryptoflux.repository.CoinsRepository
import com.marko.cryptoflux.usecases.GetCoin
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinDetailsViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	private val dispatcher = mockk<Dispatcher>()
	private val dispatchers = TestCoroutineDispatchers()
	private val repository = mockk<CoinsRepository>()
	private val getCoin = GetCoin(dispatchers, repository)

	private val viewModel: CoinDetailsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		CoinDetailsViewModel(dispatcher, dispatchers, getCoin)
	}

	private val events = ConflatedBroadcastChannel<Action>()

	init {
		runBlocking { stubEvents(events) }
	}

	@Test
	fun `is GetCoin action received`() = runBlocking {
		val coin = Factory.coin
		stubCoin(coin)

		val observer = mockObserver<Result<Coin>>()
		stubOnChange(observer)

		val action = GetCoinAction(1)
		stubDispatch(action)

		viewModel.state.observeForever(observer)

		dispatcher.dispatch(action)

		verify(exactly = 1) { observer.onChanged(Result.Success(coin)) }
	}

	private fun stubEvents(events: BroadcastChannel<Action>) = runBlocking {
		coEvery { dispatcher.events } returns events
	}

	private fun stubDispatch(action: Action) = runBlocking {
		every { dispatcher.dispatch(action) } returns Unit
		dispatcher.events.send(action)
	}

	private fun stubCoin(coin: Coin) = runBlocking {
		coEvery { repository.getCoin(any()) } returns coin
	}

	private inline fun <reified T : Any> stubOnChange(observer: Observer<T>) {
		every { observer.onChanged(any()) } returns Unit
	}

	private fun <T> mockObserver() = mockk<Observer<T>>()
}