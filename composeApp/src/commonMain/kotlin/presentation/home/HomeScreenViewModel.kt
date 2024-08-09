package presentation.home

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.AccountDetailsResponse
import domain.usecase.apiUseCases.GetAccountDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel(private val getAccountDetailsUseCase: GetAccountDetailsUseCase) : BaseViewModel() {

    private val _accountDetails = MutableStateFlow<RequestState<AccountDetailsResponse>>(Idle)
    val accountDetails: StateFlow<RequestState<AccountDetailsResponse>> get() = _accountDetails

    init {
        getAccountDetails()
    }

    fun getAccountDetails() =
        executeUseCase(block = { getAccountDetailsUseCase.execute() },
            onStart = {
                _accountDetails.update { RequestState.Loading }
            }, onError = { error ->
                _accountDetails.update { RequestState.Error(error) }
            }, onSuccess = { response ->
                _accountDetails.update { RequestState.Success(response) }
            })
}