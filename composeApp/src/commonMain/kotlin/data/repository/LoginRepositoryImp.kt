package data.repository

import core.network.utils.ApiResponse
import data.api.LoginUserService
import data.model.LoginRequest
import data.model.TokenResponse
import domain.repository.LoginRepository

class LoginRepositoryImp(private val loginUserService: LoginUserService) : LoginRepository {
    override suspend fun loginUser(loginRequest: LoginRequest): ApiResponse<TokenResponse> =
        loginUserService.loginUser(loginRequest)
}