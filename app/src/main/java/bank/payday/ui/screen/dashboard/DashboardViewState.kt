package bank.payday.ui.screen.dashboard

import bank.payday.base.view_state.BaseViewState

sealed class DashboardViewState : BaseViewState {
	object Loading : DashboardViewState()
	object Error : DashboardViewState()
	object Loaded : DashboardViewState()
	object LoadedEmpty : DashboardViewState()
}