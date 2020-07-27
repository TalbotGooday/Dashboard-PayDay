package bank.payday.ui.screen.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bank.payday.R
import bank.payday.ui.screen.sign_in.SignInActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(R.layout.activity_sign_up) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initViews()
	}

	private fun initViews() {
		sign_up.setOnClickListener { signUp() }
		sign_in.setOnClickListener { openSignInScreen() }
	}

	private fun openSignInScreen() {
		startActivity(Intent(this, SignInActivity::class.java))

		finish()
	}

	private fun signUp() {
		startActivity(Intent(this, TransactionsActivity::class.java))

		finish()
	}
}