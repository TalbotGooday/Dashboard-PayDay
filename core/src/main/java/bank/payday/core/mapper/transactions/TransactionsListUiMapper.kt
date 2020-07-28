package bank.payday.core.mapper.transactions

import bank.payday.core.extensions.getFormattedDate
import bank.payday.core.extensions.getSimpleDate
import bank.payday.core.extensions.sameDay
import bank.payday.core.extensions.toAmount
import bank.payday.core.mapper.Mapper
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.storage.db.models.transactions.DTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class TransactionsListUiMapper(
		private val withHeaders: Boolean
) : Mapper<List<DTransaction>, List<TransactionModel>> {
	private val sdfTimeIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

	override suspend fun map(from: List<DTransaction>): List<TransactionModel> = withContext(Dispatchers.IO) {
		val result = mutableListOf<TransactionModel>()

		var lastDate: Date? = null

		from.forEach {
			val date = sdfTimeIn.parse(it.date)
			if (withHeaders && lastDate.sameDay(date).not()) {
				result.add(TransactionModel(getSimpleDate(date) ?: ""))
				lastDate = date
			}

			result.add(TransactionModel(
					id = it.id,
					accountId = it.accountId,
					amount = it.amount.toAmount(),
					vendor = it.vendor,
					category = it.category,
					date = getFormattedDate(sdfTimeIn.parse(it.date)) ?: ""
			))
		}

		result
	}
}