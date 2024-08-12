package domain.usecase.localUseCases

import core.base.usecase.BaseUseCaseNoParam
import domain.repository.UserManagerRepository

class GetUserIdUseCase(private val userManagerRepository: UserManagerRepository) : BaseUseCaseNoParam<Int>() {
    override suspend fun execute(): Int = userManagerRepository.getUserId()
}