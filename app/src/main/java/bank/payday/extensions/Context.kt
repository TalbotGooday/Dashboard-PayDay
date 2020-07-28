package bank.payday.extensions

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
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
