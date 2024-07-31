package domain.usecase

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.RegisterRequest
import data.model.TokenResponse
import domain.repository.RegisterUserRepository

class RegisterUserUseCase(private val registerUserRepository: RegisterUserRepository) :
    BaseUseCase<RegisterRequest, ApiResponse<TokenResponse>>() {
    override suspend fun execute(params: RegisterRequest): ApiResponse<TokenResponse> =
        registerUserRepository.registerUser(params)

}