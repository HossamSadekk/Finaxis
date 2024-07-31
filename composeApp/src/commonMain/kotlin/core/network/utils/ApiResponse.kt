package core.network.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<out T> {
    @Serializable
    data class Success<out T>(val data: T) : ApiResponse<T>()

    @Serializable
    data class Error(val message: String) : ApiResponse<Nothing>()
}

@Serializable
data class ErrorResponseModel(
    val status: String,
    val message: String,
    val success: Boolean,
)