package bank.payday.ui.screen.launch

import bank.payday.base.view_model.BaseViewModel
import bank.payday.core.repository.CoreRepository
import bank.payday.utils.live_data.SingleLiveEvent
import kotlinx.coroutines.launch

class LaunchViewModel(
		private val coreRepository: CoreRepository
) : BaseViewModel() {
	private val _state = SingleLiveEvent<LaunchViewState>()

	val state
		get() = _state

	fun loadInitialData() {
		_state.postValue(LaunchViewState.Loading)
		launch {
			try {
				coreRepository.loadInitialData()
				val isCustomerSigned = coreRepository.isCustomerSignedIn()

				if (isCustomerSigned) {
					_state.postValue(LaunchViewState.SignedIn)
				} else {
					_state.postValue(LaunchViewState.NeedSignIn)
				}
			} catch (e: Exception) {
				_state.postValue(LaunchViewState.Error)
			}
		}
	}
}