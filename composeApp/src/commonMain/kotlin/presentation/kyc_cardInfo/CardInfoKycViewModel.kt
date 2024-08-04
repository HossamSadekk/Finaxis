package presentation.kyc_cardInfo

import core.base.usecase.TwoStringParams
import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.AccountResponse
import domain.usecase.apiUseCases.SetCardInfoKYCUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CardInfoKycViewModel(private val setCardInfoKYCUseCase: SetCardInfoKYCUseCase) : BaseViewModel() {
    private val _cardInfoState = MutableStateFlow<RequestState<AccountResponse>>(Idle)
    val cardInfoState: StateFlow<RequestState<AccountResponse>> get() = _cardInfoState

    fun submitCardInfo(cardNumber: String, cardPassword: String) {
        executeUseCase(block = {
            setCardInfoKYCUseCase.execute(
                TwoStringParams(
                    firstString = cardNumber,
                    secondString = cardPassword
                )
            )
        }, onStart = {
            _cardInfoState.update { RequestState.Loading }
        }, onError = { error ->
            _cardInfoState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _cardInfoState.update { RequestState.Success(response) }
        })
    }

}