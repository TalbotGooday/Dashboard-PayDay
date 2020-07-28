package bank.payday.ui.screen.transactions

import bank.payday.base.view_state.BaseViewState

sealed class TransactionsViewState : BaseViewState {
	object Loading : TransactionsViewState()
	object Error : TransactionsViewState()
	object Loaded : TransactionsViewState()
	object LoadedEmpty : TransactionsViewState()
}