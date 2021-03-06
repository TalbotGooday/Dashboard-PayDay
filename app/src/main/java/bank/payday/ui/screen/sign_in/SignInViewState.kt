package bank.payday.ui.screen.sign_in

import bank.payday.base.view_state.BaseViewState

sealed class SignInViewState : BaseViewState {
	object Loading : SignInViewState()
	object SignedIn : SignInViewState()
	object Error : SignInViewState()
	object ErrorCustomerNotExists : SignInViewState()
}