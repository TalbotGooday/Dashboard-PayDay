package bank.payday.ui.screen.sign_up

import bank.payday.base.view_state.BaseViewState

sealed class SignUpViewState : BaseViewState {
	object Loading : SignUpViewState()
	object Error : SignUpViewState()
	object ErrorEmail : SignUpViewState()
	object ErrorPhone : SignUpViewState()
	object SignedUp : SignUpViewState()
}