package data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val phone: String,
    val passcode: String,
)
