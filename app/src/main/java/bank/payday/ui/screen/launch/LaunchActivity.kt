package bank.payday.ui.screen.launch

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import bank.payday.R
import bank.payday.ui.screen.sign_in.SignInActivity
import bank.payday.ui.screen.transactions.TransactionsActivity

class LaunchActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.Splash)

		window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

		super.onCreate(savedInstanceState)

		openSignInScreen()
	}

	private fun openSignInScreen() {
		startActivity(Intent(this, SignInActivity::class.java))

		finish()
	}

	private fun openTransactionsScreen() {
		startActivity(Intent(this, TransactionsActivity::class.java))

		finish()
	}
}