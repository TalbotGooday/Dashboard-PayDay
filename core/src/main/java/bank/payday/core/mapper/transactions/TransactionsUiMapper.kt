package bank.payday.core.mapper.transactions

import bank.payday.core.extensions.getFormattedDate
import bank.payday.core.mapper.Mapper
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.storage.db.models.transactions.DTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

private const val AMOUNT_FORMAT = "%c $%4.3f"

class TransactionsUiMapper : Mapper<List<DTransaction>, List<TransactionModel>> {
	private val sdfTimeIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH)

	override suspend fun map(from: List<DTransaction>): List<TransactionModel> = withContext(Dispatchers.IO) {
		from.map {
			TransactionModel(
					id = it.id,
					accountId = it.accountId,
					amount = AMOUNT_FORMAT.format(it.amount.toDouble()),
					vendor = it.vendor,
					category = it.category,
					date = getFormattedDate(sdfTimeIn.parse(it.date)) ?: ""
			)
		}
	}
}