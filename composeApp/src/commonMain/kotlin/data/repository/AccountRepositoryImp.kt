package data.repository

import core.network.utils.ApiResponse
import data.api.AccountUserService
import data.model.AccountDetailsResponse
import data.model.AccountResponse
import domain.repository.AccountRepository

class AccountRepositoryImp(private val accountUserService: AccountUserService) : AccountRepository {
    override suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse> =
        accountUserService.getAccountDetails()
}