package bank.payday.ui.screen.sign_up

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.repository.CoreRepository
import kotlinx.coroutines.launch

class SignUpViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {
	private val _state = MutableLiveData<SignUpViewState>()

	val state
		get() = _state

	fun signUp(
			firstName: String,
			lastName: String,
			gender: String,
			email: String,
			password: String,
			phone: String,
			birthTimestamp: Long
	) {
		launch {
			_state.postValue(SignUpViewState.Loading)

			try {
				coreRepository.signUp(
						firstName = firstName,
						lastName = lastName,
						gender = gender,
						email = email,
						password = password,
						phone = phone,
						birthTimestamp = birthTimestamp
				)

				_state.postValue(SignUpViewState.SignedUp)
			} catch (e: Exception) {
				e.printStackTrace()
				_state.postValue(SignUpViewState.Error)
			}
		}
	}

}