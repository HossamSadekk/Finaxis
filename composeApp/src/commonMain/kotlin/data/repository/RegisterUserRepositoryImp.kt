package data.repository

import core.network.utils.ApiResponse
import data.api.RegisterUserService
import data.model.RegisterRequest
import data.model.TokenResponse
import domain.repository.RegisterUserRepository

class RegisterUserRepositoryImp(private val registerUserService: RegisterUserService) : RegisterUserRepository {
    override suspend fun registerUser(registerRequest: RegisterRequest): ApiResponse<TokenResponse> =
        registerUserService.registerUser(registerRequest)

}