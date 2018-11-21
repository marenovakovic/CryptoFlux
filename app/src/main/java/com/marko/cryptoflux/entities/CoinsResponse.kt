package com.marko.cryptoflux.entities

data class CoinsResponse(
	val data: List<Coin>,
	val metadata: ResponseMetadata
)