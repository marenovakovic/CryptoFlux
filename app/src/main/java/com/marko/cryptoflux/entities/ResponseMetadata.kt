package com.marko.cryptoflux.entities

import com.google.gson.annotations.SerializedName

data class ResponseMetadata(
	val timestamp: Long,
	@SerializedName("num_cryptocurrencies") val number: Int
)