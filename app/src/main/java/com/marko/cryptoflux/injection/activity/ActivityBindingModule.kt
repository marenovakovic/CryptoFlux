package com.marko.cryptoflux.injection.activity

import com.marko.cryptoflux.coindetails.CoinDetailsActivity
import com.marko.cryptoflux.coindetails.CoinDetailsModule
import com.marko.cryptoflux.home.HomeModule
import com.marko.cryptoflux.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [HomeModule::class])
	abstract fun mainActivity(): MainActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [CoinDetailsModule::class])
	abstract fun coinDetailsActivity(): CoinDetailsActivity
}