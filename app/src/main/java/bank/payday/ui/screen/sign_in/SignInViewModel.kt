package bank.payday.ui.screen.sign_in

import androidx.lifecycle.MutableLiveData
import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.repository.CoreRepository
import kotlinx.coroutines.launch

class SignInViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {

	private val _state = MutableLiveData<SignInViewState>()

	val state
		get() = _state

	fun signIn(login: String, password: String) {
		launch {
			_state.postValue(SignInViewState.Loading)

			try {
				coreRepository.signIn(login, password)
				_state.postValue(SignInViewState.Loaded)
			} catch (e: Exception) {
				e.printStackTrace()
				_state.postValue(SignInViewState.Error)
			}
		}
	}

}