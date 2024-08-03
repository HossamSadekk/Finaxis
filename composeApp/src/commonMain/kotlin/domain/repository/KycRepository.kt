package domain.repository

import core.network.utils.ApiResponse
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel

interface KycRepository {
    suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel>
    suspend fun setPhoneNumber(phoneNumber: String): ApiResponse<GenericResponseModel>

}