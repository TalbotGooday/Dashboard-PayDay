package bank.payday.ui.screen.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.observe
import bank.payday.BuildConfig
import bank.payday.R
import bank.payday.constants.EMAIL_MIN_LENGTH
import bank.payday.constants.PASSWORD_MIN_LENGTH
import bank.payday.extensions.toastApp
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.sign_up.SignUpActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import bank.payday.widgets.DefaultInput
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity(R.layout.activity_sign_in) {
	private val viewModel by viewModel<SignInViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.state.observe(this, ::onStateChanged)

		initViews()
	}

	private fun initViews() {
		sign_in.setOnClickListener {
			hideFieldsErrors()
			signIn()
		}

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
			SignInViewState.SignedIn -> {
				openMainScreenAndFinish()
			}
			SignInViewState.ErrorCustomerNotExists -> {
				showSignInError(R.string.error_customer_not_exists)
				setWidgetsLoadingState(false)
			}
		}
	}

	private fun signIn() {
		val login = login_input.validateLength(minLength = EMAIL_MIN_LENGTH) ?: return
		val password = password_input.validateLength(minLength = PASSWORD_MIN_LENGTH) ?: return

		viewModel.signIn(login, password)
	}

	private fun setWidgetsLoadingState(isLoading: Boolean) {
		sign_in.visibleOrInvisible(isLoading.not())
		progress.visibleOrInvisible(isLoading)

		sign_up.isEnabled = isLoading.not()
	}

	private fun openMainScreenAndFinish() {
		startActivity(Intent(this, TransactionsActivity::class.java))

		finish()
	}

	private fun openSignUpScreen() {
		startActivity(Intent(this, SignUpActivity::class.java))
	}

	private fun showSignInError(@StringRes message: Int = R.string.error_login_password) {
		toastApp(messageRes = message)
	}

	private fun hideFieldsErrors() {
		form_container.children.forEach {
			if (it is DefaultInput) {
				it.hideError()
			}
		}
	}


}