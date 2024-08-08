package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    @SerialName("amount")
    val amount: Double,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("id")
    val id: Int,
    @SerialName("note")
    val note: String,
    @SerialName("receiverAccount")
    val receiverAccount: AccountResponse,
    @SerialName("senderAccount")
    val senderAccount: AccountResponse,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String,
)