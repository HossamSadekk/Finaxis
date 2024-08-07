package presentation.passcode

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import data.model.LoginRequest
import data.model.RegisterRequest
import data.model.TokenResponse
import domain.usecase.apiUseCases.LoginUserUseCase
import domain.usecase.apiUseCases.RegisterUserUseCase
import domain.usecase.localUseCases.GetUserLoggedInUseCase
import domain.usecase.localUseCases.GetUserPasscodeUseCase
import domain.usecase.localUseCases.IsUserHasAccountUseCase
import domain.usecase.localUseCases.SaveUserLoggedInUseCase
import domain.usecase.localUseCases.SaveUserPasscodeUseCase
import domain.usecase.localUseCases.SaveUserTokenUseCase
import domain.usecase.localUseCases.SetUserHasAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PasscodeViewmodel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserLoggedInUseCase: SaveUserLoggedInUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveUserPasscodeUseCase: SaveUserPasscodeUseCase,
    private val getUserLoggedInUseCase: GetUserLoggedInUseCase,
    private val isUserHasAccountUseCase: IsUserHasAccountUseCase,
    private val getUserPasscodeUseCase: GetUserPasscodeUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val setUserHasAccountUseCase: SetUserHasAccountUseCase,
) : BaseViewModel() {
    private val _tokenState = MutableStateFlow<RequestState<TokenResponse>>(RequestState.Idle)
    val tokenState: StateFlow<RequestState<TokenResponse>> get() = _tokenState

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _isUserHasAccount = MutableStateFlow(false)
    val isUserHasAccount: StateFlow<Boolean> get() = _isUserHasAccount

    private val _passcode = MutableStateFlow("")
    val passcode: StateFlow<String> get() = _passcode

    init {
        safeLaunch {
            _isLoggedIn.value = getUserLoggedInUseCase.execute()
            _isUserHasAccount.value = isUserHasAccountUseCase.execute()
            _passcode.value = getUserPasscodeUseCase.execute()
        }
    }

    fun registerUser(registerRequest: RegisterRequest) =
        executeUseCase(block = { registerUserUseCase.execute(registerRequest) },
            onStart = {
                _tokenState.update { RequestState.Loading }
            }, onError = { error ->
                _tokenState.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _tokenState.update { RequestState.Success(response) }
            }
        )

    fun loginUser(loginRequest: LoginRequest) =
        executeUseCase(block = { loginUserUseCase.execute(loginRequest) },
            onStart = {
                _tokenState.update { RequestState.Loading }
            }, onError = { error ->
                _tokenState.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _tokenState.update { RequestState.Success(response) }
            })

    fun cacheUserLoginState(token: String) =
        safeLaunch {
            saveUserTokenUseCase.execute(token)
            saveUserLoggedInUseCase.execute(true)
        }

    fun saveUserPasscode(passcode: String) = safeLaunch {
        saveUserPasscodeUseCase.execute(passcode)
    }

    fun resetErrorState() {
        _tokenState.value = RequestState.Idle
    }

    fun setUserHasAccount() = safeLaunch {
        setUserHasAccountUseCase.execute(true)
    }
}