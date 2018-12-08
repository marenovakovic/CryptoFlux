package com.marko.cryptoflux.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.cryptoflux.R
import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.GetCoinsAction
import com.marko.cryptoflux.coindetails.CoinDetailsActivity
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.extensions.startActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * [Activity] that will fetch and displays list of [Coin]s
 */
class MainActivity : DaggerAppCompatActivity() {

	@Inject
	lateinit var dispatcher: Dispatcher

	@Inject
	lateinit var factory: CoinsViewModelFactory

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	private val coinsAdapter = CoinsAdapter {
		startActivity<CoinDetailsActivity>(CoinDetailsActivity.EXTRA_COIN_ID to it)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		dispatcher.dispatch(GetCoinsAction)

		viewModel.state.observe(this, Observer {
			when (it) {
				is Result.Success -> {
					progressBar.hide()
					coinsAdapter.coins = it.data
				}
				is Result.Error -> {
					progressBar.hide()
					Timber.e(it.exception)
					Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
				}
			}
		})

		recyclerView.adapter = coinsAdapter
		recyclerView.layoutManager =
				LinearLayoutManager(this).apply { isItemPrefetchEnabled = true }
		recyclerView.setHasFixedSize(true)
	}
}
