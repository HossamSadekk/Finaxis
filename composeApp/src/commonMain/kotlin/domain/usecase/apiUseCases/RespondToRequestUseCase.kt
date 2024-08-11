package domain.usecase.apiUseCases

import core.network.utils.ApiResponse
import data.model.TransactionResponseModel
import domain.repository.AccountRepository

class RespondToRequestUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(transactionId: String, accept: Boolean): ApiResponse<TransactionResponseModel> =
        accountRepository.respondToMoneyRequest(transactionId, accept)
}