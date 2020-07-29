package bank.payday.extensions

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import bank.payday.R


fun Context.toastApp(message: String? = null, @StringRes messageRes: Int? = null, duration: Int = Toast.LENGTH_LONG) {
	val messageFin = when {
		messageRes != null -> getString(messageRes)
		else -> message
	}

	Toast(this).apply {
		this.view = LayoutInflater.from(this@toastApp)
				.inflate(R.layout.layout_toast, null)
				.apply {
					findViewById<TextView>(R.id.custom_toast_text).text = messageFin
				}
		this.duration = duration
		this.show()
	}
}

fun Context.color(@ColorRes colorRes: Int): Int {
	return ContextCompat.getColor(this, colorRes)
}
