package com.marko.cryptoflux.entities

import com.google.gson.annotations.SerializedName

typealias CoinId = Int

data class Coin(
	val id: Int,
	val name: String,
	val symbol: String,
	@SerializedName("website_slug") val websiteSlub: String
)