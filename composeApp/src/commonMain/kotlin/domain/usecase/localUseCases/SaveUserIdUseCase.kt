package domain.usecase.localUseCases

import core.base.usecase.BaseUseCase
import domain.repository.UserManagerRepository

class SaveUserIdUseCase(private val userManagerRepository: UserManagerRepository) : BaseUseCase<Int, Unit>() {
    override suspend fun execute(params: Int) = userManagerRepository.saveUserId(params)
}