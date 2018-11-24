package com.marko.cryptoflux.home

import android.os.Bundle
import android.widget.Toast
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
	lateinit var store: CoinsStore

	@Inject
	lateinit var dispatcher: Dispatcher

	private val coinsAdapter = CoinsAdapter {
		startActivity<CoinDetailsActivity>(CoinDetailsActivity.EXTRA_COIN_ID to it)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		dispatcher.dispatch(GetCoinsAction)

		store.observe(this) {
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
		}

		recyclerView.adapter = coinsAdapter
		recyclerView.layoutManager =
				LinearLayoutManager(this).apply { isItemPrefetchEnabled = true }
		recyclerView.setHasFixedSize(true)
	}
}
