package presentation.send_receive_money

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.RequestDetailsResponse
import data.model.TransactionResponseModel
import domain.usecase.apiUseCases.RequestDetailsUseCase
import domain.usecase.apiUseCases.RequestMoneyUseCase
import domain.usecase.apiUseCases.TransferMoneyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MoneyRequestViewModel(
    private val requestDetailsUseCase: RequestDetailsUseCase,
    private val transferMoneyUseCase: TransferMoneyUseCase,
    private val requestMoneyUseCase: RequestMoneyUseCase,
) : BaseViewModel() {
    private val _requestDetailsState = MutableStateFlow<RequestState<RequestDetailsResponse>>(Idle)
    val requestDetailsState: StateFlow<RequestState<RequestDetailsResponse>> get() = _requestDetailsState

    private val _transferMoneyState = MutableStateFlow<RequestState<TransactionResponseModel>>(Idle)
    val transferMoneyState: StateFlow<RequestState<TransactionResponseModel>> get() = _transferMoneyState

    fun getRequestDetails(username: String) =
        executeUseCase(block = { requestDetailsUseCase.execute(username) }, onStart = {
            _requestDetailsState.update { RequestState.Loading }
        }, onError = { error ->
            _requestDetailsState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _requestDetailsState.update { RequestState.Success(response) }
        })

    fun transferMoney(senderName: String, receiverName: String, amount: Double, note: String) =
        executeUseCase(block = {
            transferMoneyUseCase.execute(senderName, receiverName, amount, note)
        }, onStart = {
            _transferMoneyState.update { RequestState.Loading }
        }, onError = { error ->
            _transferMoneyState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _transferMoneyState.update { RequestState.Success(response) }
        })

    private val _requestMoneyState = MutableStateFlow<RequestState<TransactionResponseModel>>(Idle)
    val requestMoneyState: StateFlow<RequestState<TransactionResponseModel>> get() = _requestMoneyState

    fun requestMoney(senderName: String, receiverName: String, amount: Double, note: String) =
        executeUseCase(block = {
            requestMoneyUseCase.execute(senderName, receiverName, amount, note)
        }, onStart = {
            _requestMoneyState.update { RequestState.Loading }
        }, onError = { error ->
            _requestMoneyState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _requestMoneyState.update { RequestState.Success(response) }
        })

    fun resetErrorState() {
        _requestDetailsState.value = Idle
    }

    fun resetTransferState() {
        _transferMoneyState.value = Idle
    }

}