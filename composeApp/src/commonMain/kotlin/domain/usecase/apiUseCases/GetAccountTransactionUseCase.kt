package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.Transaction
import domain.repository.AccountRepository

class GetAccountTransactionUseCase(private val accountRepository: AccountRepository) :
    BaseUseCase<Int, ApiResponse<List<Transaction>>>() {
    override suspend fun execute(params: Int): ApiResponse<List<Transaction>> =
        accountRepository.getTransactionList(params)
}