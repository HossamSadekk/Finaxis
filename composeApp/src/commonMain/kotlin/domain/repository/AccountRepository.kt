package domain.repository

import core.network.utils.ApiResponse
import data.model.AccountDetailsResponse
import data.model.AccountResponse

interface AccountRepository {
    suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse>

}