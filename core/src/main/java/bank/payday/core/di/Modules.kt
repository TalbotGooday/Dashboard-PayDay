package bank.payday.core.di

import bank.payday.core.repository.CoreRepository
import bank.payday.network.di.netModule
import bank.payday.network.repository.ApiRepository
import bank.payday.storage.di.databaseModule
import bank.payday.storage.repository.StorageRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val coreModule = module {
	loadKoinModules(listOf(netModule, databaseModule))

	fun provideCoreRepository(
			apiRepository: ApiRepository,
			storageRepository: StorageRepository
	): CoreRepository {
		return CoreRepository(apiRepository, storageRepository)
	}

	single { provideCoreRepository(get(), get()) }
}