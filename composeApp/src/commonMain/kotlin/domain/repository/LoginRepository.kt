package domain.repository

import core.network.utils.ApiResponse
import data.model.LoginRequest
import data.model.TokenResponse

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): ApiResponse<TokenResponse>
}