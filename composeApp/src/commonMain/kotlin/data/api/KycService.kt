package data.api

import core.network.utils.ApiResponse
import data.model.AccountResponse
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel

interface KycService {
    suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel>
    suspend fun setPhoneNumber(phoneNumber: String): ApiResponse<GenericResponseModel>
    suspend fun setUsername(username: String): ApiResponse<GenericResponseModel>
    suspend fun setCardInfo(cardNumber: String, cardPassword: String): ApiResponse<AccountResponse>

}