package bank.payday.ui.screen.transactions

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.models.transactions.TransactionModel
import kotlinx.coroutines.launch

class TransactionsViewModel : BaseViewModel() {
	val _transactions = MutableLiveData<List<TransactionModel>>()

	val transactions
		get() = _transactions

	fun getTransactions() {
		launch {

		}
	}
}