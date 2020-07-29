package bank.payday.ui.screen.launch

import bank.payday.base.view_state.BaseViewState

sealed class LaunchViewState : BaseViewState {
	object Loading : LaunchViewState()
	object Error : LaunchViewState()
	object SignedIn : LaunchViewState()
	object NeedSignIn : LaunchViewState()
}