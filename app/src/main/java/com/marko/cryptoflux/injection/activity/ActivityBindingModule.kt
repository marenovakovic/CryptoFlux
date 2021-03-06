package com.marko.cryptoflux.injection.activity

import com.marko.cryptoflux.coindetails.CoinDetailsActivity
import com.marko.cryptoflux.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector
	abstract fun mainActivity(): MainActivity

	@ActivityScope
	@ContributesAndroidInjector
	abstract fun coinDetailsActivity(): CoinDetailsActivity
}