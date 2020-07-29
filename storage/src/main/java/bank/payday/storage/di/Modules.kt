package bank.payday.storage.di

import android.content.Context
import bank.payday.storage.BuildConfig
import bank.payday.storage.db.PdDatabase
import bank.payday.storage.repository.StorageRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {

	fun provideDatabase(context: Context, useInMemory: Boolean): PdDatabase {
		return PdDatabase.create(context, useInMemory)
	}

	fun provideStorageRepository(
			db: PdDatabase
	): StorageRepository {
		return StorageRepository(
				db = db
		)
	}

	single { provideDatabase(androidContext(), BuildConfig.DEBUG) }

	single { provideStorageRepository(get()) }
}
