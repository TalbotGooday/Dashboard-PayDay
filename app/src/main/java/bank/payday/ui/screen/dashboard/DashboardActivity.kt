package bank.payday.ui.screen.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bank.payday.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(R.layout.activity_dashboard) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initViews()
	}

	private fun initViews() {
		action_back.setOnClickListener { finish() }
	}
}