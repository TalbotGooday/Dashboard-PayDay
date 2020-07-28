package bank.payday.core.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.abs

private const val AMOUNT_FORMAT = "%c $%4.3f"

fun String.toAmount(): String {
	val amountDouble = toDouble()
	val symbol = if (amountDouble < 0) '-' else '+'

	return AMOUNT_FORMAT.format(symbol, abs(amountDouble))
}

fun Double.toAmount(): String {
	val symbol = if (this < 0) '-' else '+'

	return AMOUNT_FORMAT.format(symbol, abs(this))
}


fun Double.toDashboardSum(): String {
	val df = DecimalFormat("$ ###,###,##0")
	df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ENGLISH)

	return df.format(this)
}