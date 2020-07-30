package bank.payday.storage.repository

import bank.payday.storage.db.PdDatabase
import bank.payday.storage.db.models.customers.DCustomer
import bank.payday.storage.db.models.customers.DUser
import bank.payday.storage.db.models.transactions.DTransaction

class StorageRepository(
		private val db: PdDatabase
) {
	suspend fun saveCustomers(data: List<DCustomer>) = db.customersDao().addAll(data)

	suspend fun getTransactions(): List<DTransaction> {
		val customer = getCurrentCustomer() ?: return emptyList()

		return db.transactionsDao().getAll(customer.id)
	}

	suspend fun getDebit(dateStart: String, dateEnd: String): List<DTransaction> {
		val customer = getCurrentCustomer() ?: return emptyList()

		return db.transactionsDao().getDebit(customer.id, dateStart, dateEnd)
	}

	suspend fun getCredit(dateStart: String, dateEnd: String): List<DTransaction> {
		val customer = getCurrentCustomer() ?: return emptyList()

		return db.transactionsDao().getCredit(customer.id, dateStart, dateEnd)
	}

	suspend fun saveTransactions(data: List<DTransaction>) = db.transactionsDao().addAll(data)

	suspend fun saveCurrentCustomer(user: DUser) = db.customersDao().saveCurrentUser(user)

	suspend fun getCurrentCustomer() = db.customersDao().getCurrentLogged()

	suspend fun getCustomerByEmail(email: String) = db.customersDao().getCustomerByEmail(email)

	suspend fun getCustomerByPhone(phone: String) = db.customersDao().getCustomerByPhone(phone)

}