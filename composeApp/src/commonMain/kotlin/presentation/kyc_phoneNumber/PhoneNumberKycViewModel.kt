package presentation.kyc_phoneNumber

import core.base.viewmodel.BaseViewModel
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.GenericResponseModel
import domain.usecase.apiUseCases.SetPhoneNumberKYCUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PhoneNumberKycViewModel(private val setPhoneNumberKYCUseCase: SetPhoneNumberKYCUseCase) : BaseViewModel() {

    private val _phoneNumberState = MutableStateFlow<RequestState<GenericResponseModel>>(Idle)
    val phoneNumberState: StateFlow<RequestState<GenericResponseModel>> get() = _phoneNumberState

    fun setPhoneNumber(phoneNumber: String) {
        executeUseCase(block = { setPhoneNumberKYCUseCase.execute(phoneNumber) }, onStart = {
            _phoneNumberState.update { RequestState.Loading }
        }, onError = { error ->
            _phoneNumberState.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _phoneNumberState.update { RequestState.Success(response) }
        })
    }
    fun resetErrorState() {
        _phoneNumberState.value = Idle
    }

}