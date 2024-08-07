package data.api

import core.network.utils.ApiResponse
import data.model.LoginRequest
import data.model.TokenResponse

interface LoginUserService {
    suspend fun loginUser(loginRequest: LoginRequest): ApiResponse<TokenResponse>

}