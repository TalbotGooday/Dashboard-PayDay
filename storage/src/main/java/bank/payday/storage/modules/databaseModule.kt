package bank.payday.storage.modules

import android.content.Context
import bank.payday.storage.BuildConfig
import bank.payday.storage.db.PdDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

	fun provideDatabase(context: Context, useInMemory: Boolean): PdDatabase {
		return PdDatabase.create(context, useInMemory)
	}

	single { provideDatabase(androidContext(), BuildConfig.DEBUG) }
}
