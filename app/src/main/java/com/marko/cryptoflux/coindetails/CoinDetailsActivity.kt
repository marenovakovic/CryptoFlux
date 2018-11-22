package com.marko.cryptoflux.coindetails

import android.os.Bundle
import android.widget.Toast
import com.marko.cryptoflux.R
import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.GetCoinAction
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.CoinId
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_coin_details.*
import timber.log.Timber
import javax.inject.Inject

/**
 * [Activity] that will fetch and display [Coin] details
 */
class CoinDetailsActivity : DaggerAppCompatActivity() {

	companion object {
		const val EXTRA_COIN_ID = "coin_id"
	}

	@Inject
	lateinit var store: CoinDetailsStore

	@Inject
	lateinit var dispatcher: Dispatcher

	private val coinId: CoinId by lazy(LazyThreadSafetyMode.NONE) {
		intent.getIntExtra(EXTRA_COIN_ID, - 1)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_coin_details)

		dispatcher.dispatch(GetCoinAction(id = coinId))

		store.observe(this) {
			when (it) {
				is Result.Success -> {
					coinDetailsProgressbar.hide()
					coinDetailsId.text = it.data.id.toString()
					coinDetailsName.text = it.data.name
					coinDetailsSymbol.text = it.data.symbol
					coinDetailsWebsiteSlug.text = it.data.websiteSlub
				}
				is Result.Error -> {
					coinDetailsProgressbar.hide()
					Timber.e(it.exception)
					Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
				}
			}
		}
	}
}
