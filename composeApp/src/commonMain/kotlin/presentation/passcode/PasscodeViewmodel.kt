package presentation.passcode

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import data.model.RegisterRequest
import data.model.TokenResponse
import domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PasscodeViewmodel(private val registerUserUseCase: RegisterUserUseCase) : BaseViewModel() {
    private val _tokenState = MutableStateFlow<RequestState<TokenResponse>>(RequestState.Idle)
    val tokenState: StateFlow<RequestState<TokenResponse>> get() = _tokenState

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

    fun resetErrorState() {
        _tokenState.value = RequestState.Idle
    }
}