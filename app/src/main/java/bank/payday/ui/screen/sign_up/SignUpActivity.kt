package bank.payday.ui.screen.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.observe
import bank.payday.R
import bank.payday.extensions.invisible
import bank.payday.extensions.toastApp
import bank.payday.extensions.visible
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.sign_in.SignInActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import bank.payday.widgets.DefaultInput
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity(R.layout.activity_sign_up) {
	private val viewModel by viewModel<SignUpViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.state.observe(this, ::onStateChanged)

		initViews()
	}

	private fun initViews() {
		sign_up.setOnClickListener {
			hideFieldsErrors()
			signUp()
		}

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
		startActivity(Intent(this, SignInActivity::class.java).apply {
			addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
		})

		finish()
	}

	private fun signUp() {
		val firstName = first_name_input.validateLength() ?: return
		val lastName = last_name_input.validateLength() ?: return
		val phone = phone_input.validateLength(minLength = 8) ?: return
		val email = email_input.validateLength(minLength = 5) ?: return

		val password = password_input.validateLength(minLength = 6) ?: return

		confirm_password_input.validatePassword(minLength = 6, reference = password) ?: return

		val birthDate = validateBirthDate() ?: return

		val gender = if (gender_group.checkedRadioButtonId == R.id.gender_male) {
			"male"
		} else {
			"female"
		}

		viewModel.signUp(
				firstName = firstName,
				lastName = lastName,
				gender = gender,
				email = email,
				password = password,
				phone = phone,
				birthTimestamp = birthDate
		)
	}

	private fun validateBirthDate(): Long? {
		return try {
			val bDay = day_input.text?.toString() ?: ""
			val bMonth = month_input.text?.toString() ?: ""
			val bYear = year_input.text?.toString() ?: ""

			SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).apply {
				isLenient = false
			}.parse("$bDay.$bMonth.$bYear")?.time.let {
				it ?: throw RuntimeException()
			}
		} catch (e: Exception) {
			date_error.visible()
			null
		}
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

	private fun hideFieldsErrors() {
		form_container.children.forEach {
			if (it is DefaultInput) {
				it.hideError()
			} else if (it.tag == "error") {
				it.invisible()
			}
		}
	}

	private fun showSignUpError() {
		toastApp(messageRes = R.string.error_login_password)
	}

}