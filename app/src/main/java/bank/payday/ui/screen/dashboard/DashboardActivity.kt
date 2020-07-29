package bank.payday.ui.screen.dashboard

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.core.view.children
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import bank.payday.R
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.extensions.*
import bank.payday.ui.screen.dashboard.adapters.DashboardAdapter
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
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

		switchDebitCredit(action_debit)
	}

	private fun initViews() {
		action_back.setOnClickListener { finish() }
		action_debit.setOnClickListener { switchDebitCredit(it) }
		action_credit.setOnClickListener { switchDebitCredit(it) }
		action_date_pick.setOnClickListener { openDatePicker() }

		action_clear_date_pick.setOnClickListener { disableDateFilter() }
		initDashboardList()
	}

	private fun openDatePicker() {
		val range = CalendarConstraints.Builder().build()

		val pickerBuilder = MaterialDatePicker.Builder
				.dateRangePicker()
				.setCalendarConstraints(range)

		if (viewModel.dateStart != 0L) {
			pickerBuilder.setSelection(Pair(viewModel.dateStart, viewModel.dateEnd))
		}

		val picker = pickerBuilder.build()

		picker.addOnPositiveButtonClickListener {
			val dateStart = it.first
			val dateEnd = it.second

			enableDateFilter(dateStart, dateEnd)
		}

		picker.show(supportFragmentManager, "MaterialDatePicker")
	}

	private fun enableDateFilter(dateStart: Long?, dateEnd: Long?) {
		action_clear_date_pick.visible()

		viewModel.getDashboardTransactions(
				dateStart = dateStart,
				dateEnd = dateEnd
		)
	}

	private fun disableDateFilter() {
		action_clear_date_pick.gone()

		viewModel.getDefaultDashboardTransactions()
	}

	private fun switchDebitCredit(it: View) {
		val isDebit = it.id == R.id.action_debit
		viewModel.getDashboardTransactions(isDebit = isDebit)

		setSelected(it.id)
	}

	private fun setSelected(viewId: Int) {
		container_menu.children.forEach {
			if (it.tag == "button" && it is TextView) {
				val font = if (it.id == viewId) {
					R.font.montserrat_bold
				} else {
					R.font.montserrat
				}

				it.typeface = ResourcesCompat.getFont(this, font)
			}
		}
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