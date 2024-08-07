package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.LoginRequest
import data.model.TokenResponse
import domain.repository.LoginRepository

class LoginUserUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<LoginRequest, ApiResponse<TokenResponse>>() {
    override suspend fun execute(params: LoginRequest): ApiResponse<TokenResponse> =
        loginRepository.loginUser(params)
}