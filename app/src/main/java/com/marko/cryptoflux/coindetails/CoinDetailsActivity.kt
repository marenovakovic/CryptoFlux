package com.marko.cryptoflux.coindetails

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marko.cryptoflux.R
import com.marko.cryptoflux.Result
import com.marko.cryptoflux.actions.GetCoinAction
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.entities.Coin
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
	lateinit var dispatcher: Dispatcher

	@Inject
	lateinit var factory: CoinDetailsViewModelFactory

	private val viewModel: CoinDetailsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(this, factory).get(CoinDetailsViewModel::class.java)
	}

	private val coinId: CoinId by lazy(LazyThreadSafetyMode.NONE) {
		intent.getIntExtra(EXTRA_COIN_ID, - 1)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_coin_details)

		dispatcher.dispatch(GetCoinAction(id = coinId))

		viewModel.state.observe(this, Observer(::handleResult))
	}

	private fun handleResult(result: Result<Coin>) = when (result) {
		is Result.Loading -> Unit
		is Result.Success -> {
			coinDetailsProgressbar.hide()
			populateUi(result.data)
		}
		is Result.Error -> {
			coinDetailsProgressbar.hide()
			Timber.e(result.exception)
			Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
		}
	}

	private fun populateUi(coin: Coin) {
		coinDetailsId.text = coin.id.toString()
		coinDetailsName.text = coin.name
		coinDetailsSymbol.text = coin.symbol
		coinDetailsWebsiteSlug.text = coin.websiteSlub
	}
}
