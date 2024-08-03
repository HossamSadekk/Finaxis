package data.api

import core.network.utils.ApiResponse
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel

interface KycService {
    suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel>
    suspend fun setPhoneNumber(phoneNumber: String): ApiResponse<GenericResponseModel>
}