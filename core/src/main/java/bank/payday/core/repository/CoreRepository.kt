package bank.payday.core.repository

import bank.payday.core.mapper.customer.CustomerMapper
import bank.payday.core.mapper.customer.DCustomersMapper
import bank.payday.core.mapper.dashboard.DashboardListUiMapper
import bank.payday.core.mapper.transactions.TransactionsListDbMapper
import bank.payday.core.mapper.transactions.TransactionsListUiMapper
import bank.payday.core.models.customer.Customer
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.network.repository.ApiRepository
import bank.payday.storage.repository.StorageRepository

class CoreRepository(
		private val apiRepository: ApiRepository,
		private val storageRepository: StorageRepository
) {
	suspend fun signIn(email: String, password: String): Customer {
		val response = apiRepository.signIn(email, password)

		return CustomerMapper().map(response)
	}

	suspend fun signUp(
			firstName: String,
			lastName: String,
			gender: String,
			email: String,
			password: String,
			phone: String,
			birthTimestamp: Long
	): Customer {
		val response = apiRepository.signUp(
				firstName,
				lastName,
				gender,
				email,
				password,
				phone,
				birthTimestamp
		)

		return CustomerMapper().map(response)
	}

	suspend fun getCustomers() {
		try {
			val data = apiRepository.loadCustomers()
			storageRepository.saveCustomers(DCustomersMapper().map(data))
		} catch (e: Exception) {
		}
	}

	suspend fun getTransactions(withHeaders: Boolean = true, refresh: Boolean = false): List<TransactionModel> {
		val dbResults = storageRepository.getTransactions()
		val mapper = TransactionsListUiMapper(withHeaders)

		return try {
			if (refresh || dbResults.isEmpty()) {
				val result = apiRepository.loadTransactions()

				storageRepository.saveTransactions(TransactionsListDbMapper().map(result))
				mapper.map(storageRepository.getTransactions())
			} else {
				mapper.map(dbResults)
			}
		} catch (e: Exception) {
			e.printStackTrace()
			mapper.map(dbResults)
		}
	}

	suspend fun getDashboardTransactions(
			isDebit: Boolean,
			dateStart: String,
			dateEnd: String,
			withHeaders: Boolean = true
	): List<DashboardModel> {
		val dbResults = if (isDebit) {
			storageRepository.getDebit(dateStart, dateEnd)
		} else {
			storageRepository.getCredit(dateStart, dateEnd)
		}

		return DashboardListUiMapper(withHeaders).map(dbResults)
	}
}