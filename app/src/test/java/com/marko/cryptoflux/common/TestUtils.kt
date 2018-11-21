package com.marko.cryptoflux.common

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

object TestUtils {

	private val gson = Gson()

	fun <T> loadGson(path: String, type: Class<T>): T {
		val json = getJson(path)
		return gson.fromJson(json, type)
	}

	private fun getJson(path: String): String {
		val stringBuilder = StringBuilder()
		val reader = BufferedReader(
			InputStreamReader(
				this::class.java.classLoader !!.getResourceAsStream(path)
			)
		)

		for (line in reader.lines()) {
			stringBuilder.append(line)
		}
		return stringBuilder.toString()
	}
}