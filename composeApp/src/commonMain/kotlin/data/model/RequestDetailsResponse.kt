package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDetailsResponse(
    @SerialName("receiverCardNumber")
    val receiverCardNumber: String,
    @SerialName("receiverName")
    val receiverName: String,
    @SerialName("senderBalance")
    val senderBalance: String,
    @SerialName("senderUsername")
    val senderUsername: String,
)