package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponseModel(
    @SerialName("amount")
    val amount: Double,
    @SerialName("note")
    val note: String,
    @SerialName("receiverUsername")
    val receiverUsername: String,
    @SerialName("senderUsername")
    val senderUsername: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("transactionId")
    val transactionId: Int,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
)