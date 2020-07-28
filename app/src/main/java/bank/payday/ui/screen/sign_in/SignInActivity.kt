package bank.payday.ui.screen.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import bank.payday.BuildConfig
import bank.payday.R
import bank.payday.extensions.toastApp
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.sign_up.SignUpActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity(R.layout.activity_sign_in) {
	companion object {
		private const val MIN_PASSWORD_LENGTH = 6
	}

	private val viewModel by viewModel<SignInViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.state.observe(this, ::onStateChanged)

		initViews()
	}

	private fun initViews() {
		sign_in.setOnClickListener { signIn() }
		sign_up.setOnClickListener { openSignUpScreen() }

		if (BuildConfig.DEBUG) {
			login_input.setText("Nadiah.Spoel@example.com")
			password_input.setText("springs")
		}
	}

	private fun onStateChanged(state: SignInViewState) {
		when (state) {
			SignInViewState.Loading -> {
				setWidgetsLoadingState(true)
			}
			SignInViewState.Error -> {
				showSignInError()
				setWidgetsLoadingState(false)
			}
			SignInViewState.Loaded -> {
				openMainScreenAndFinish()
			}
		}
	}

	private fun signIn() {
		val login = login_input.text?.toString() ?: return
		val password = password_input.text?.toString() ?: return

		if (login.isBlank()) return
		if (password.isBlank()) return

		if (password.length < MIN_PASSWORD_LENGTH) {
			toastApp(messageRes = R.string.error_password_length)

			return
		}

		viewModel.signIn(login, password)
	}

	private fun openMainScreenAndFinish() {
		startActivity(Intent(this, TransactionsActivity::class.java))

		finish()
	}

	private fun openSignUpScreen() {
		startActivity(Intent(this, SignUpActivity::class.java))
	}

	private fun showSignInError() {
		toastApp(messageRes = R.string.error_login_password)
	}


	private fun setWidgetsLoadingState(isLoading: Boolean) {
		sign_in.visibleOrInvisible(isLoading.not())
		progress.visibleOrInvisible(isLoading)

		sign_up.isEnabled = isLoading
	}
}