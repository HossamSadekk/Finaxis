package data.api.serviceImp

import core.network.ktorHelpers.getRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.ACCOUNT_DETAILS
import data.api.AccountUserService
import data.model.AccountDetailsResponse
import data.model.AccountResponse
import io.ktor.client.HttpClient

class AccountUserServiceImp(private val httpClient: HttpClient) : AccountUserService {
    override suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse> =
        httpClient.getRequest(path = ACCOUNT_DETAILS)
}