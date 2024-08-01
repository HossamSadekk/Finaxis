package domain.usecase.localUseCases

import core.base.usecase.BaseUseCaseNoParam
import domain.repository.UserManagerRepository

class GetUserLoggedInUseCase(private val userManagerRepository: UserManagerRepository) : BaseUseCaseNoParam<Boolean>() {
    override suspend fun execute(): Boolean = userManagerRepository.isUserLoggedIn()

}