package bank.payday.di

import bank.payday.ui.screen.dashboard.DashboardViewModel
import bank.payday.ui.screen.launch.LaunchViewModel
import bank.payday.ui.screen.sign_in.SignInViewModel
import bank.payday.ui.screen.sign_up.SignUpViewModel
import bank.payday.ui.screen.transactions.TransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { LaunchViewModel(get()) }
	viewModel { SignInViewModel(get()) }
	viewModel { TransactionsViewModel(get()) }
	viewModel { DashboardViewModel(get()) }
	viewModel { SignUpViewModel(get()) }
}