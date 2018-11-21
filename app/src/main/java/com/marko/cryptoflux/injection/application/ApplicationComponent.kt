package com.marko.cryptoflux.injection.application

import android.app.Application
import com.marko.cryptoflux.app.App
import com.marko.cryptoflux.app.AppModule
import com.marko.cryptoflux.injection.activity.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		AppModule::class,
		ActivityBindingModule::class
	]
)
interface ApplicationComponent : AndroidInjector<App>

fun Application.buildComponent(app: App) =
		DaggerApplicationComponent.builder()
			.appModule(AppModule(app))
			.build()