package data.api.serviceImp

import core.network.ktorHelpers.postRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.KYC_PHONE_NUMBER
import core.network.utils.Endpoints.KYC_SELECT_BANK
import core.network.utils.Parameters.PHONE_NUMBER
import data.api.KycService
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel
import io.ktor.client.HttpClient

class KycServiceImp(private val httpClient: HttpClient) : KycService {
    override suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel> =
        httpClient.postRequest(path = KYC_SELECT_BANK, requestBody = selectBankRequestModel)

    override suspend fun setPhoneNumber(phoneNumber: String): ApiResponse<GenericResponseModel> =
        httpClient.postRequest(path = KYC_PHONE_NUMBER, queryParams = mapOf(PHONE_NUMBER to phoneNumber))
}