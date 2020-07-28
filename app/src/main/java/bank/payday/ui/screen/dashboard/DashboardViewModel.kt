package bank.payday.ui.screen.dashboard

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.models.dashboard.DashboardModel
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.core.repository.CoreRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {
	private val _transactions = MutableLiveData<List<DashboardModel>>()
	private val _state = MutableLiveData<DashboardViewState>()

	val transactions
		get() = _transactions

	val state
		get() = _state

	fun getDashboardTransactions() {
		_state.postValue(DashboardViewState.Loading)
		launch {
			try {
				val transactions = coreRepository.getDashboardTransactions()
				if (transactions.isEmpty()) {
					_state.postValue(DashboardViewState.LoadedEmpty)
				} else {
					_state.postValue(DashboardViewState.Loaded)
					_transactions.value = transactions
				}
			} catch (e: Exception) {
				e.printStackTrace()
				_state.postValue(DashboardViewState.Error)
			}
		}
	}
}