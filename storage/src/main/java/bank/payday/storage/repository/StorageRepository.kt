package bank.payday.storage.repository

import bank.payday.storage.db.PdDatabase
import bank.payday.storage.db.models.transactions.DTransaction

class StorageRepository(
		val db: PdDatabase
) {
	suspend fun getCustomers() = db.customersDao().getAll()

	suspend fun getTransactions() = db.transactionsDao().getAll()

	suspend fun getDashboardExpenses() = db.transactionsDao().getAllExpenses()

	suspend fun saveTransactions(data: List<DTransaction>) = db.transactionsDao().addAll(data)
}