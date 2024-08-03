package data.api.serviceImp

import core.network.ktorHelpers.postRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.REGISTER
import data.api.RegisterUserService
import data.model.RegisterRequest
import data.model.TokenResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class RegisterUserServiceImp(private val httpClient: HttpClient) : RegisterUserService {
    override suspend fun registerUser(registerRequest: RegisterRequest): ApiResponse<TokenResponse> =
        httpClient.postRequest<TokenResponse>(
            path = REGISTER,
            requestBody = registerRequest
        )
}