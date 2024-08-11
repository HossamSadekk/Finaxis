package domain.usecase.localUseCases

import core.base.usecase.BaseUseCaseNoParam
import domain.repository.UserManagerRepository

class GetUsernameUseCase(private val userManagerRepository: UserManagerRepository) : BaseUseCaseNoParam<String>() {
    override suspend fun execute(): String = userManagerRepository.getUsername()
}