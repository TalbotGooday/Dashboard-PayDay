package bank.payday.ui.screen.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import bank.payday.R
import bank.payday.extensions.toastApp
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.sign_in.SignInActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.sign_up
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity(R.layout.activity_sign_up) {
	private val viewModel by viewModel<SignUpViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.state.observe(this, ::onStateChanged)

		initViews()
	}

	private fun initViews() {
		sign_up.setOnClickListener { signUp() }
		sign_in.setOnClickListener { openSignInScreen() }
	}

	private fun onStateChanged(state: SignUpViewState) {
		when (state) {
			SignUpViewState.Loading -> {
				setWidgetsLoadingState(true)
			}
			SignUpViewState.Error -> {
				showSignUpError()
				setWidgetsLoadingState(false)
			}
			SignUpViewState.SignedUp -> {
				openMainScreenAndFinish()
			}
		}
	}

	private fun openSignInScreen() {
		startActivity(Intent(this, SignInActivity::class.java))

		finish()
	}

	private fun signUp() {
		val firstName = first_name_input.text?.toString()
		val lastName = last_name_input.text?.toString()
		val phone = phone_input.text?.toString()
		val email = email_input.text?.toString()

		val password = password_input.text?.toString()
		val passwordConfirm = confirm_password_input.text?.toString()

		val bDay = day_input.text?.toString()
		val bMonth = month_input.text?.toString()
		val bYear = year_input.text?.toString()

		val gender = if (gender_group.checkedRadioButtonId == R.id.gender_male) {
			"male"
		} else {
			"female"
		}

		val birthTimestamp = first_name_input.text?.toString()

//		viewModel.signUp(
//				firstName = firstName,
//				lastName = lastName,
//				gender = gender,
//				email = email,
//				password = password,
//				phone = phone,
//				birthTimestamp = birthTimestamp
//		)
	}

	private fun openMainScreenAndFinish() {
		startActivity(Intent(this, TransactionsActivity::class.java).apply {
			addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
		})
	}

	private fun setWidgetsLoadingState(isLoading: Boolean) {
		sign_up.visibleOrInvisible(isLoading.not())
		progress.visibleOrInvisible(isLoading)

		sign_up.isEnabled = isLoading
	}

	private fun showSignUpError() {
		toastApp(messageRes = R.string.error_login_password)
	}

}