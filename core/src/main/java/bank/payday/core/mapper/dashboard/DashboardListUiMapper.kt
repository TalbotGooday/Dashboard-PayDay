package bank.payday.core.mapper.dashboard

import bank.payday.core.extensions.*
import bank.payday.core.mapper.Mapper
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.storage.db.models.transactions.DTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class DashboardListUiMapper(
		private val withHeaders: Boolean
) : Mapper<List<DTransaction>, List<DashboardModel>> {
	private val sdfTimeIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

	override suspend fun map(from: List<DTransaction>): List<DashboardModel> = withContext(Dispatchers.IO) {
		val result = mutableListOf<DashboardModel>()

		var lastDate: Date? = null
		var amountSum = 0.0
		var lastHeader: DashboardModel? = null

		from.forEach {
			val date = sdfTimeIn.parse(it.date)

			if (withHeaders && lastDate.sameMonth(date).not()) {
				lastHeader?.amount = amountSum.toDashboardSum()

				lastHeader = DashboardModel(date = getSimpleMonthYear(date = date) ?: "")

				lastHeader?.let { header -> result.add(header) }

				lastDate = date
				amountSum = 0.0
			}

			amountSum += abs(it.amount)

			result.add(DashboardModel(
					date = getFormattedDate(sdfTimeIn.parse(it.date)) ?: "",
					amount = it.amount.toAmount(),
					title = it.category
			))
		}

		lastHeader?.amount = amountSum.toDashboardSum()

		result
	}

}