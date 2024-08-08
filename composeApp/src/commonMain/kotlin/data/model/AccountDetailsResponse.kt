package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetailsResponse(
    @SerialName("balance")
    val balance: Double,
    @SerialName("bankId")
    val bankId: Int,
    @SerialName("cardNumber")
    val cardNumber: String,
    @SerialName("id")
    val id: Int,
    @SerialName("transactions")
    val transactions: List<Transaction>,
    @SerialName("username")
    val username: String,
)