package presentation.kyc_username

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.GenericResponseModel
import domain.usecase.apiUseCases.SetUsernameKYCUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class KycUsernameViewModel(private val setUsernameKYCUseCase: SetUsernameKYCUseCase) : BaseViewModel() {
    private val _usernameState = MutableStateFlow<RequestState<GenericResponseModel>>(Idle)
    val usernameState: StateFlow<RequestState<GenericResponseModel>> get() = _usernameState

    fun setUsername(username: String) {
        executeUseCase(block = { setUsernameKYCUseCase.execute(username) }, onStart = {
            _usernameState.update { RequestState.Loading }
        }, onError = { error ->
            _usernameState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _usernameState.update { RequestState.Success(response) }
        })
    }

    fun resetUsernameState() {
        _usernameState.value = Idle
    }

}