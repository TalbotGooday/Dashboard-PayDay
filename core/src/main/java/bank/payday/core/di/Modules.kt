package bank.payday.core.di

import bank.payday.core.repository.CoreRepository
import bank.payday.network.di.netModule
import bank.payday.network.repository.NetworkRepository
import bank.payday.storage.di.storageModule
import bank.payday.storage.repository.StorageRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val coreModule = module {
	loadKoinModules(listOf(netModule, storageModule))

	fun provideCoreRepository(
			networkRepository: NetworkRepository,
			storageRepository: StorageRepository
	): CoreRepository {
		return CoreRepository(networkRepository, storageRepository)
	}

	single { provideCoreRepository(get(), get()) }
}
