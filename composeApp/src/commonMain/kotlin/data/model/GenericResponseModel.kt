package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenericResponseModel(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String,
)