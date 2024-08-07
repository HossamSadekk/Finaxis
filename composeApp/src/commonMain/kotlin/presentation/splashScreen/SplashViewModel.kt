package presentation.splashScreen

import core.base.viewmodel.BaseViewModel
import domain.usecase.localUseCases.GetUserLoggedInUseCase
import domain.usecase.localUseCases.IsUserHasAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel(
    private val getUserLoggedInUseCase: GetUserLoggedInUseCase,
    private val isUserHasAccountUseCase: IsUserHasAccountUseCase,
) : BaseViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _isUserHasAccount = MutableStateFlow(false)
    val isUserHasAccount: StateFlow<Boolean> get() = _isUserHasAccount

    init {
        safeLaunch {
            _isLoggedIn.value = getUserLoggedInUseCase.execute()
            _isUserHasAccount.value = isUserHasAccountUseCase.execute()
        }
    }
}