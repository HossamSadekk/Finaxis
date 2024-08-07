package domain.usecase.localUseCases

import core.base.usecase.BaseUseCase
import domain.repository.UserManagerRepository

class SetUserHasAccountUseCase(private val userManagerRepository: UserManagerRepository):BaseUseCase<Boolean,Unit>() {
    override suspend fun execute(params: Boolean) = userManagerRepository.userHasAccount(params)
}