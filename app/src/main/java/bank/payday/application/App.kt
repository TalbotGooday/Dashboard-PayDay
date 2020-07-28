package bank.payday.application

import android.app.Application
import bank.payday.core.di.coreModule
import bank.payday.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		initKoin()
	}

	private fun initKoin() {
		startKoin {
			androidContext(this@App)
			androidLogger(Level.DEBUG)

			modules(listOf(coreModule, viewModelModule))
		}
	}
}