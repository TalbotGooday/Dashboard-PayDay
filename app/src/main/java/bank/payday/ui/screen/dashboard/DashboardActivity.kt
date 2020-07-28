package bank.payday.ui.screen.dashboard

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import bank.payday.R
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.extensions.visibleOrGone
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.dashboard.adapters.DashboardAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity(R.layout.activity_dashboard) {
	private val viewModel by viewModel<DashboardViewModel>()
	private val dashboardAdapter = getDashboardAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


		viewModel.transactions.observe(this, ::onGotTransactions)
		viewModel.state.observe(this, ::onStateChanged)

		initViews()

		viewModel.getDashboardTransactions()
	}

	private fun initViews() {
		action_back.setOnClickListener { finish() }

		initDashboardList()
	}

	private fun initDashboardList() {
		data_list.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@DashboardActivity)
			adapter = dashboardAdapter
		}
	}

	private fun onStateChanged(state: DashboardViewState) {
		when (state) {
			DashboardViewState.Loading -> {
				showPlaceholder(false)
				setWidgetsLoadingState(true)
			}
			DashboardViewState.Error -> {
				showPlaceholder(true, R.string.error_load_dashboard)
				setWidgetsLoadingState(false)
			}
			DashboardViewState.Loaded -> {
				showPlaceholder(false)
				setWidgetsLoadingState(false)
			}
			DashboardViewState.LoadedEmpty -> {
				showPlaceholder(true, R.string.dashboard_placeholder_text)
				setWidgetsLoadingState(false)
			}
		}
	}

	private fun getDashboardAdapter() = DashboardAdapter()

	private fun onGotTransactions(data: List<DashboardModel>) {
		dashboardAdapter.swapData(data)
	}


	private fun showPlaceholder(isVisible: Boolean, @StringRes text: Int = 0) {
		error_placeholder.apply {
			visibleOrGone(isVisible)
			if (text != 0) {
				setText(text)
			}
		}
	}

	private fun setWidgetsLoadingState(isLoading: Boolean) {
		progress.visibleOrInvisible(isLoading)
	}

}