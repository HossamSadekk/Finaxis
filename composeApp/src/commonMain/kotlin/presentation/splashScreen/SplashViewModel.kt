package presentation.splashScreen

import core.base.viewmodel.BaseViewModel
import domain.usecase.localUseCases.GetUserLoggedInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel(private val getUserLoggedInUseCase: GetUserLoggedInUseCase) : BaseViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    init {
        safeLaunch {
            _isLoggedIn.value = getUserLoggedInUseCase.execute()
        }
    }
}