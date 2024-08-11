package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.TransactionResponseModel
import domain.repository.AccountRepository

class GetPendingRequestsUseCase(private val accountRepository: AccountRepository) :
    BaseUseCase<String, ApiResponse<List<TransactionResponseModel>>>() {
    override suspend fun execute(params: String): ApiResponse<List<TransactionResponseModel>> =
        accountRepository.getPendingRequestTransactions(params)
}