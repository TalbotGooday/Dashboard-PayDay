package bank.payday.di

import bank.payday.ui.screen.sign_in.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { SignInViewModel(get()) }
}