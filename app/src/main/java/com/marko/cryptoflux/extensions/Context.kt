package com.marko.cryptoflux.extensions

import android.content.Context
import android.content.Intent
import com.marko.cryptoflux.entities.CoinId

inline fun <reified T> Context.intentFor() = Intent(this, T::class.java)

inline fun <reified T> Context.startActivity(vararg extras: Pair<String, Any>) =
	startActivity(
		intentFor<T>()
			.apply {
				for ((name, extra) in extras) {
					when (extra) {
						is Int -> putExtra(name, extra)
						is String -> putExtra(name, extra)
					}
				}
			}
	)