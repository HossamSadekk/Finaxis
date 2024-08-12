package presentation.transaction

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.Transaction
import domain.usecase.apiUseCases.GetAccountTransactionUseCase
import domain.usecase.localUseCases.GetUserIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TransactionViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getAccountTransactionUseCase: GetAccountTransactionUseCase,
) : BaseViewModel() {
    private val _transactionState = MutableStateFlow<RequestState<List<Transaction>>>(Idle)
    val transactionState: StateFlow<RequestState<List<Transaction>>> get() = _transactionState

    init {
        getAccountId()
    }

    fun getTransactionList() =
        executeUseCase(block = { getAccountTransactionUseCase.execute(getUserIdUseCase.execute()) },
            onStart = {
                _transactionState.update { RequestState.Loading }
            }, onError = { error ->
                _transactionState.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _transactionState.update { RequestState.Success(response) }
            })

    private val _accountState = MutableStateFlow(8)
    val accountState: StateFlow<Int> get() = _accountState
    fun getAccountId() = safeLaunch {
        _accountState.update { getUserIdUseCase.execute() }
    }

    fun resetState() {
        _transactionState.value = Idle
    }

}