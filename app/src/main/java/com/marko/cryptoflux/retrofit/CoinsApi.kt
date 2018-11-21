package com.marko.cryptoflux.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marko.cryptoflux.repository.CoinsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * CoinsApi builder class
 */
object CoinsApi {

	private const val BASE_URL = " https://api.coinmarketcap.com/v2/"
	private const val WRITE_TIMEOUT = 15L
	private const val READ_TIMEOUT = 15L

	private val okHttpClient = OkHttpClient.Builder()
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
		.build()

	private val retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.client(okHttpClient)
		.addCallAdapterFactory(CoroutineCallAdapterFactory())
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	val coinsService: CoinsService = retrofit.create(CoinsService::class.java)
}