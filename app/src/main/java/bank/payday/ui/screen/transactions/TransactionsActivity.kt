package bank.payday.ui.screen.transactions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bank.payday.R
import bank.payday.ui.screen.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_transactions.*

class TransactionsActivity : AppCompatActivity(R.layout.activity_transactions) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initViews()
	}

	private fun initViews() {
		action_dashboard.setOnClickListener { openDashboardScreen() }
	}

	private fun openDashboardScreen() {
		startActivity(Intent(this, DashboardActivity::class.java))
	}
}