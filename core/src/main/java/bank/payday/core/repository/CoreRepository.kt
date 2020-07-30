package bank.payday.core.repository

import bank.payday.core.mapper.customer.CustomerMapper
import bank.payday.core.mapper.customer.DCustomersMapper
import bank.payday.core.mapper.customer.DUserMapper
import bank.payday.core.mapper.dashboard.DashboardListUiMapper
import bank.payday.core.mapper.transactions.TransactionsListDbMapper
import bank.payday.core.mapper.transactions.TransactionsListUiMapper
import bank.payday.core.models.customer.Customer
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.network.repository.NetworkRepository
import bank.payday.storage.repository.StorageRepository

class CoreRepository(
		private val networkRepository: NetworkRepository,
		private val storageRepository: StorageRepository
) {
	suspend fun loadInitialData() {
		val data = networkRepository.loadCustomers()
		storageRepository.saveCustomers(DCustomersMapper().map(data))
	}

	suspend fun isCustomerSignedIn() = storageRepository.getCurrentCustomer() != null

	suspend fun signIn(email: String, password: String): Customer {
		val response = networkRepository.signIn(email, password)

		storageRepository.saveCurrentCustomer(DUserMapper().map(response))

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
		val response = networkRepository.signUp(
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

	suspend fun isCustomerEmailFree(email: String) = storageRepository.getCustomerByEmail(email) == null

	suspend fun isCustomerPhoneFree(phone: String) = storageRepository.getCustomerByPhone(phone) == null

	suspend fun getTransactions(withHeaders: Boolean = true, refresh: Boolean = false): List<TransactionModel> {
		val dbResults = storageRepository.getTransactions()
		val mapper = TransactionsListUiMapper(withHeaders)

		return try {
			if (refresh || dbResults.isEmpty()) {
				val result = networkRepository.loadTransactions()

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