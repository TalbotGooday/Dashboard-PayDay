package bank.payday.core.mapper.transactions

import bank.payday.core.mapper.Mapper
import bank.payday.network.models.transactions.NTransaction
import bank.payday.storage.db.models.transactions.DTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionsDbMapper : Mapper<List<NTransaction>, List<DTransaction>> {
	override suspend fun map(from: List<NTransaction>): List<DTransaction> = withContext(Dispatchers.IO){
		from.map {
			DTransaction(
					id = it.id,
					accountId = it.accountId,
					amount = it.amount,
					vendor = it.vendor,
					category = it.category,
					date = it.date
			)
		}
	}
}