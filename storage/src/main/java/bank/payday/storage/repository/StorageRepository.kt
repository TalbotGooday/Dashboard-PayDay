package bank.payday.storage.repository

import bank.payday.storage.db.PdDatabase
import bank.payday.storage.db.models.customers.DCustomer
import bank.payday.storage.db.models.transactions.DTransaction

class StorageRepository(
		private val db: PdDatabase
) {
	suspend fun getCustomers() = db.customersDao().getAll()

	suspend fun saveCustomers(data: List<DCustomer>) = db.customersDao().addAll(data)

	suspend fun getTransactions() = db.transactionsDao().getAll()

	suspend fun getDebit(dateStart: String, dateEnd: String): List<DTransaction> {
//		val customer = getCurrentCustomer() ?: return emptyList()

		return db.transactionsDao().getDebit(/*customer.id,*/ dateStart, dateEnd)
	}

	suspend fun getCredit(dateStart: String, dateEnd: String): List<DTransaction> {
//		val customer = getCurrentCustomer() ?: return emptyList()

		return db.transactionsDao().getCredit(/*customer.id,*/ dateStart, dateEnd)
	}

	suspend fun saveTransactions(data: List<DTransaction>) = db.transactionsDao().addAll(data)

	suspend fun getCurrentCustomer() = db.customersDao().getCurrentLogged()
}