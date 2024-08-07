package data.api.serviceImp

import core.network.ktorHelpers.postRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints
import data.api.LoginUserService
import data.model.LoginRequest
import data.model.TokenResponse
import io.ktor.client.HttpClient

class LoginServiceImp(private val httpClient: HttpClient) : LoginUserService {
    override suspend fun loginUser(loginRequest: LoginRequest): ApiResponse<TokenResponse> =
        httpClient.postRequest(
            path = Endpoints.LOGIN,
            requestBody = loginRequest
        )
}