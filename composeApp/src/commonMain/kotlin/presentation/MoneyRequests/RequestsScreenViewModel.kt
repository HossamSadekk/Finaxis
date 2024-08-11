package presentation.MoneyRequests

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.TransactionResponseModel
import domain.usecase.apiUseCases.GetPendingRequestsUseCase
import domain.usecase.apiUseCases.RespondToRequestUseCase
import domain.usecase.localUseCases.GetUsernameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RequestsScreenViewModel(
    private val getUsernameUseCase: GetUsernameUseCase,
    private val getPendingRequestsUseCase: GetPendingRequestsUseCase,
    private val respondToRequestUseCase: RespondToRequestUseCase,
) : BaseViewModel() {
    private val _requestsState = MutableStateFlow<RequestState<List<TransactionResponseModel>>>(Idle)
    val requestsState: StateFlow<RequestState<List<TransactionResponseModel>>> get() = _requestsState

    init {
        getPendingRequests()
    }

    fun getPendingRequests() =
        executeUseCase(block = { getPendingRequestsUseCase.execute(getUsernameUseCase.execute()) },
            onStart = {
                _requestsState.update { RequestState.Loading }
            }, onError = { error ->
                _requestsState.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _requestsState.update { RequestState.Success(response) }
            })

    private val _respondState = MutableStateFlow<RequestState<TransactionResponseModel>>(Idle)
    val respondState: StateFlow<RequestState<TransactionResponseModel>> get() = _respondState

    fun respondToRequest(transactionId: String, accept: Boolean) =
        executeUseCase(block = { respondToRequestUseCase.execute(transactionId, accept) },
            onStart = {
                _respondState.update { RequestState.Loading }
            }, onError = { error ->
                _requestsState.update { RequestState.Error(error) }
                _respondState.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _requestsState.value.let { currentState ->
                    if (currentState is RequestState.Success) {
                        // Filter out the accepted/refused request
                        val updatedList = currentState.data.filter { it.transactionId != transactionId.toInt() }
                        _requestsState.update { RequestState.Success(updatedList) }
                    }
                }
            })

    fun resetState() {
        _requestsState.value = Idle
    }

}