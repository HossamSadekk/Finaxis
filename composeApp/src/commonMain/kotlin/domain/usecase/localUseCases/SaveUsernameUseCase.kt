package domain.usecase.localUseCases

import core.base.usecase.BaseUseCase
import domain.repository.UserManagerRepository

class SaveUsernameUseCase(private val userManagerRepository: UserManagerRepository) : BaseUseCase<String, Unit>() {
    override suspend fun execute(params: String) = userManagerRepository.saveUsername(params)
}