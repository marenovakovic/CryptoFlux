package com.marko.cryptoflux.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> LiveData<T>.distinct(): LiveData<T> {
	val mediatorLiveData = MediatorLiveData<T>()
	mediatorLiveData.addSource(this) {
		if (it != mediatorLiveData.value) mediatorLiveData.value = it
	}
	return mediatorLiveData
}