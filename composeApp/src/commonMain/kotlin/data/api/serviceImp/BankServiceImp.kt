package data.api.serviceImp

import core.network.ktorHelpers.getRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.BANK
import data.api.BankService
import data.model.BankResponse
import io.ktor.client.HttpClient

class BankServiceImp(private val httpClient: HttpClient) : BankService {
    override suspend fun getBanks(): ApiResponse<BankResponse> =
        httpClient.getRequest<BankResponse>(path = BANK)
}