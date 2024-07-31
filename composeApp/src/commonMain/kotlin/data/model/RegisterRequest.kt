package data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val phone: String,
    val passcode: String,
)
