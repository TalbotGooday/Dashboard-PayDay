package bank.payday.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(date: Date?, locale: Locale = Locale.ENGLISH): String? {
	date ?: return null

	val cal: Calendar = Calendar.getInstance()
	cal.time = date
	val day: Int = cal.get(Calendar.DATE)
	return if (day !in 11..18) when (day % 10) {
		1 -> SimpleDateFormat("MMMM d'st', yyyy", locale).format(date)
		2 -> SimpleDateFormat("MMMM 'nd', yyyy", locale).format(date)
		3 -> SimpleDateFormat("MMMM d'rd', yyyy", locale).format(date)
		else -> SimpleDateFormat("MMMM d'th', yyyy", locale).format(date)
	} else SimpleDateFormat("MMMM d'th', yyyy", locale).format(date)
}

fun getSimpleDate(date: Date?, locale: Locale = Locale.ENGLISH): String? {
	date ?: return null

	val cal = Calendar.getInstance()
	cal.time = date

	val now = Calendar.getInstance()

	return when {
		now[Calendar.DAY_OF_YEAR] == cal[Calendar.DAY_OF_YEAR]
				&& now[Calendar.YEAR] == cal[Calendar.YEAR] -> {
			"Today"
		}
		now[Calendar.DATE] - cal[Calendar.DATE] == 1 && now[Calendar.YEAR] == cal[Calendar.YEAR] -> {
			"Yesterday"
		}
		else -> {
			SimpleDateFormat("MMMM d", locale).format(date)
		}
	}
}

fun getSimpleMonthYear(date: Date?, locale: Locale = Locale.ENGLISH): String? {
	date ?: return null

	return SimpleDateFormat("MMMM yyyy", locale).format(date)
}

fun Date?.sameDay(date: Date?): Boolean {
	this ?: return false
	date ?: return false

	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()
	cal1.time = this
	cal2.time = date
	return cal1[Calendar.DAY_OF_YEAR] == cal2[Calendar.DAY_OF_YEAR] &&
			cal1[Calendar.YEAR] == cal2[Calendar.YEAR]
}

fun Date?.sameMonth(date: Date?): Boolean {
	this ?: return false
	date ?: return false

	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()
	cal1.time = this
	cal2.time = date
	return cal1[Calendar.MONTH] == cal2[Calendar.MONTH] &&
			cal1[Calendar.YEAR] == cal2[Calendar.YEAR]
}
