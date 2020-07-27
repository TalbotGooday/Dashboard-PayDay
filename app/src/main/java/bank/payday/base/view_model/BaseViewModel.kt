package bank.payday.base.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel() : ViewModel(), CoroutineScope {
	override val coroutineContext: CoroutineContext
		get() = SupervisorJob() + Dispatchers.Main

}