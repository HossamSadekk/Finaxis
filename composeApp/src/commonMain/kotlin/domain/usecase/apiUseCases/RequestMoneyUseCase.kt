package domain.usecase.apiUseCases

import core.network.utils.ApiResponse
import data.model.TransactionResponseModel
import domain.repository.AccountRepository

class RequestMoneyUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(
        senderName: String,
        receiverName: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel> =
        accountRepository.requestMoney(senderName, receiverName, amount, note)
}