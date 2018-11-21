package com.marko.cryptoflux.app

import android.content.Context
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchersImpl
import com.marko.cryptoflux.dispatcher.Dispatcher
import com.marko.cryptoflux.dispatcher.DispatcherImpl
import com.marko.cryptoflux.repository.CoinsRepository
import com.marko.cryptoflux.repository.CoinsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppBindingModule {

	@Binds
	abstract fun bindCoroutineDispatchers(bind: CoroutineDispatchersImpl): CoroutineDispatchers

	@Binds
	abstract fun bindDispatcher(bind: DispatcherImpl): Dispatcher

	@Binds
	abstract fun bindCoinsRepository(bind: CoinsRepositoryImpl): CoinsRepository
}

@Module(includes = [AppBindingModule::class])
class AppModule(private val app: App) {

	@Provides
	fun provideContext(): Context = app
}