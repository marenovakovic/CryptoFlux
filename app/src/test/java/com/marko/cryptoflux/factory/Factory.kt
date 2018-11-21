package com.marko.cryptoflux.factory

import com.marko.cryptoflux.entities.Coin

object Factory {

	val coin: Coin = Coin(
		id = 1,
		name = "bitcoin",
		symbol = "BTC",
		websiteSlub = "slug"
	)

	val coins: List<Coin> = listOf(coin, coin)
}