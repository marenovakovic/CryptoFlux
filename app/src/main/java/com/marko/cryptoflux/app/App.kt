package com.marko.cryptoflux.app

import com.marko.cryptoflux.injection.application.buildComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component

	private val component = buildComponent(this)
}