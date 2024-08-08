package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCaseNoParam
import core.network.utils.ApiResponse
import data.model.AccountDetailsResponse
import data.model.AccountResponse
import domain.repository.AccountRepository

class GetAccountDetailsUseCase(private val accountRepository: AccountRepository) :
    BaseUseCaseNoParam<ApiResponse<AccountDetailsResponse>>() {
    override suspend fun execute(): ApiResponse<AccountDetailsResponse> = accountRepository.getAccountDetails()
}