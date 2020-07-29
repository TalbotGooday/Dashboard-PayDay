package bank.payday.ui.screen.dashboard

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.core.repository.CoreRepository
import bank.payday.extensions.toUTC
import kotlinx.coroutines.launch
import java.util.*

class DashboardViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {
	private val _transactions = MutableLiveData<List<DashboardModel>>()
	private val _state = MutableLiveData<DashboardViewState>()

	val transactions
		get() = _transactions

	val state
		get() = _state

	var isDebit = true
		private set

	var dateStart = 0L
		private set
	var dateEnd = Date().time
		private set

	fun getDefaultDashboardTransactions() {
		getDashboardTransactions(
				isDebit = isDebit,
				dateStart = 0,
				dateEnd = Date().time
		)
	}

	fun getDashboardTransactions(
			isDebit: Boolean = this.isDebit,
			dateStart: Long? = this.dateStart,
			dateEnd: Long? = this.dateEnd
	) {
		this.isDebit = isDebit
		this.dateStart = dateStart ?: 0
		this.dateEnd = dateEnd ?: Date().time

		_state.postValue(DashboardViewState.Loading)
		launch {
			try {
				val transactions = coreRepository.getDashboardTransactions(
						isDebit = isDebit,
						dateStart = dateStart?.toUTC() ?: 0L.toUTC(),
						dateEnd = dateEnd?.toUTC() ?: Date().toUTC()
				)
				if (transactions.isEmpty()) {
					_state.postValue(DashboardViewState.LoadedEmpty)
				} else {
					_state.postValue(DashboardViewState.Loaded)
				}

				_transactions.value = transactions
			} catch (e: Exception) {
				e.printStackTrace()
				_state.postValue(DashboardViewState.Error)
			}
		}
	}
}