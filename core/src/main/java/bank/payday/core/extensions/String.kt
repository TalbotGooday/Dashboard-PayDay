package bank.payday.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(date: Date?, locale: Locale = Locale.ENGLISH): String? {
	date ?: return null

	val cal: Calendar = Calendar.getInstance()
	cal.time = date
	//2nd of march 2015
	val day: Int = cal.get(Calendar.DATE)
	return if (day !in 11..18) when (day % 10) {
		1 -> SimpleDateFormat("d'st' 'of' MMMM yyyy", locale).format(date)
		2 -> SimpleDateFormat("d'nd' 'of' MMMM yyyy", locale).format(date)
		3 -> SimpleDateFormat("d'rd' 'of' MMMM yyyy", locale).format(date)
		else -> SimpleDateFormat("d'th' 'of' MMMM yyyy", locale).format(date)
	} else SimpleDateFormat("d'th' 'of' MMMM yyyy", locale).format(date)
}