package data.api

import core.network.utils.ApiResponse
import data.model.AccountDetailsResponse
import data.model.AccountResponse

interface AccountUserService {
    suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse>

}