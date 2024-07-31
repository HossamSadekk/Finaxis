package domain.repository

import core.network.utils.ApiResponse
import data.model.RegisterRequest
import data.model.TokenResponse

interface RegisterUserRepository {
   suspend fun registerUser(registerRequest: RegisterRequest): ApiResponse<TokenResponse>
}