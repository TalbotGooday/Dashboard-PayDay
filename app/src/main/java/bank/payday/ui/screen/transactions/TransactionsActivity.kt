package bank.payday.ui.screen.transactions

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import bank.payday.R
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.extensions.visibleOrGone
import bank.payday.extensions.visibleOrInvisible
import bank.payday.ui.screen.dashboard.DashboardActivity
import bank.payday.ui.screen.transactions.adapters.TransactionsAdapter
import kotlinx.android.synthetic.main.activity_transactions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionsActivity : AppCompatActivity(R.layout.activity_transactions) {

	private val viewModel by viewModel<TransactionsViewModel>()

	private val transactionsAdapter = getTransactionsAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.transactions.observe(this, ::onGotTransactions)
		viewModel.state.observe(this, ::onStateChanged)

		initViews()

		viewModel.getTransactions()
	}

	private fun initViews() {
		action_dashboard.setOnClickListener { openDashboardScreen() }

		initTransactionsList()
	}

	private fun initTransactionsList() {
		data_list.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@TransactionsActivity)
			adapter = transactionsAdapter
		}
	}

	private fun getTransactionsAdapter() = TransactionsAdapter(object : TransactionsAdapter.Listener {
		override fun onItemClick(item: TransactionModel) {

		}
	})

	private fun openDashboardScreen() {
		startActivity(Intent(this, DashboardActivity::class.java))
	}

	private fun onGotTransactions(data: List<TransactionModel>) {
		transactionsAdapter.swapData(data)
	}

	private fun onStateChanged(state: TransactionsViewState) {
		when (state) {
			TransactionsViewState.Loading -> {
				showPlaceholder(false)
				setWidgetsLoadingState(true)
			}
			TransactionsViewState.Error -> {
				showPlaceholder(true, R.string.error_load_transactions)
				setWidgetsLoadingState(false)
			}
			TransactionsViewState.Loaded -> {
				showPlaceholder(false)
				setWidgetsLoadingState(false)
			}
			TransactionsViewState.LoadedEmpty -> {
				showPlaceholder(true, R.string.transactions_placeholder_text)
				setWidgetsLoadingState(false)
			}
		}
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