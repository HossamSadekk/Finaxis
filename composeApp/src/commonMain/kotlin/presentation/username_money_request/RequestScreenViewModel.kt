package presentation.username_money_request

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import domain.usecase.apiUseCases.CheckIfUserExistUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RequestScreenViewModel(private val checkIfUserExistUseCase: CheckIfUserExistUseCase) : BaseViewModel() {
    private val _isUserExist = MutableStateFlow<RequestState<Boolean>>(Idle)
    val isUserExist: StateFlow<RequestState<Boolean>> get() = _isUserExist

    fun checkIfUserExist(username: String) =
        executeUseCase(block = { checkIfUserExistUseCase.execute(username) }, onStart = {
            _isUserExist.update { RequestState.Loading }
        }, onError = { error ->
            _isUserExist.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _isUserExist.update { RequestState.Success(response) }
        })

    fun resetState() {
        _isUserExist.value = RequestState.Idle
    }
}