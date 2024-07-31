package data.api

import core.network.utils.ApiResponse
import data.model.RegisterRequest
import data.model.TokenResponse

interface RegisterUserService {
    suspend fun registerUser(registerRequest: RegisterRequest): ApiResponse<TokenResponse>
}