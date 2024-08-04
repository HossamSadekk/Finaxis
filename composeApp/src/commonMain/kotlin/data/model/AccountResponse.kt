package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    @SerialName("balance")
    val balance: Double,
    @SerialName("bank")
    val bank: Bank,
    @SerialName("cardNumber")
    val cardNumber: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("id")
    val id: Int,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("username")
    val username: String,
)