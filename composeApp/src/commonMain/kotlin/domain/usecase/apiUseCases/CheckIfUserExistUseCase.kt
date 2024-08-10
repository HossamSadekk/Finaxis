package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import domain.repository.AccountRepository

class CheckIfUserExistUseCase(private val accountRepository: AccountRepository) :
    BaseUseCase<String, ApiResponse<Boolean>>() {
    override suspend fun execute(params: String): ApiResponse<Boolean> =
        accountRepository.checkIfUsernameExist(params)
}