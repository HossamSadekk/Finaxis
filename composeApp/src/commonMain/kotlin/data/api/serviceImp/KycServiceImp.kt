package data.api.serviceImp

import core.network.ktorHelpers.postRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.KYC_SELECT_BANK
import data.api.KycService
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel
import io.ktor.client.HttpClient

class KycServiceImp(private val httpClient: HttpClient) : KycService {
    override suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel> {
        return httpClient.postRequest(path = KYC_SELECT_BANK, requestBody = selectBankRequestModel)
    }
}