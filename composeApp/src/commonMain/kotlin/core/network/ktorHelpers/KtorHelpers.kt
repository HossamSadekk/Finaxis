package core.network.ktorHelpers

import core.network.utils.ApiResponse
import core.network.utils.ErrorResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.http.path

// Generic function for making requests
suspend inline fun <reified T> HttpClient.getRequest(
    path: String,
    queryParams: Map<String, String> = emptyMap(),
): T {
    return this.get {
        url {
            path(path)
            queryParams.forEach { (key, value) ->
                parameters.append(key, value)
            }
        }
    }.body()
}

suspend inline fun <reified T, reified R> HttpClient.postRequest(
    path: String,
    requestBody: R,
    queryParams: Map<String, String> = emptyMap(),
): ApiResponse<T> {
    return try {
        val response: HttpResponse = this.post {
            url {
                path(path)
                queryParams.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
            setBody(requestBody)
        }

        if (response.status.isSuccess()) {
            val responseBody: T = response.body()
            ApiResponse.Success(responseBody)
        } else {
            val errorResponse: ErrorResponseModel = response.body()
            ApiResponse.Error(errorResponse.message)
        }
    } catch (e: Exception) {
        ApiResponse.Error(e.message ?: "An unknown error occurred")
    }
}

// interceptors
fun HttpClientConfig<*>.addTokenInterceptor(tokenProvider: () -> String) {
    install(DefaultRequest) {
        headers {
            val token = tokenProvider()
            if (token.isNotEmpty()) {
                append("Authorization", "Bearer $token")
            }
        }
    }
}