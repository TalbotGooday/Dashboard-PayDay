package bank.payday.ui.screen.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bank.payday.R
import bank.payday.ui.screen.sign_up.SignUpActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(R.layout.activity_sign_in) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initViews()
	}

	private fun initViews() {
		sign_in.setOnClickListener { signIn() }
		sign_up.setOnClickListener { openSignUpScreen() }
	}

	private fun signIn() {
		startActivity(Intent(this, TransactionsActivity::class.java))
	}

	private fun openSignUpScreen() {
		startActivity(Intent(this, SignUpActivity::class.java))
	}
}