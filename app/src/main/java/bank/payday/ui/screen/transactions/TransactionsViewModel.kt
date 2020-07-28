package bank.payday.ui.screen.transactions

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.models.transactions.TransactionModel
import bank.payday.core.repository.CoreRepository
import kotlinx.coroutines.launch

class TransactionsViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {
	private val _transactions = MutableLiveData<List<TransactionModel>>()
	private val _state = MutableLiveData<TransactionsViewState>()

	val transactions
		get() = _transactions

	val state
		get() = _state

	fun getTransactions() {
		_state.postValue(TransactionsViewState.Loading)
		launch {
			try {
				val transactions = coreRepository.getTransactions()
				if (transactions.isEmpty()) {
					_state.postValue(TransactionsViewState.LoadedEmpty)
				} else {
					_state.postValue(TransactionsViewState.Loaded)
					_transactions.value = transactions
				}
			} catch (e: Exception) {
				e.printStackTrace()
				_state.postValue(TransactionsViewState.Error)
			}
		}
	}
}