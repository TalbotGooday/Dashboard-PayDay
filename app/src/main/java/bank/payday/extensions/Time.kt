package bank.payday.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toUTC(): String {
	return Date(this).toUTC()
}


fun Date.toUTC(): String {
	val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

	return df.format(this)
}