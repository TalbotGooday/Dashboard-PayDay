package bank.payday.ui.screen.launch

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import bank.payday.R
import bank.payday.ui.screen.sign_in.SignInActivity
import bank.payday.ui.screen.transactions.TransactionsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchActivity : AppCompatActivity() {

	private val viewModel by viewModel<LaunchViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		requestWindowFeature(Window.FEATURE_NO_TITLE)

		window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

		super.onCreate(savedInstanceState)

		viewModel.state.observe(this, ::onStateChanged)

		viewModel.loadInitialData()
	}

	private fun onStateChanged(state: LaunchViewState) {
		when (state) {
			LaunchViewState.Loading -> {
			}
			LaunchViewState.Error -> {
			}
			LaunchViewState.SignedIn -> {
				openTransactionsScreen()
			}
			LaunchViewState.NeedSignIn -> {
				openSignInScreen()
			}
		}
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