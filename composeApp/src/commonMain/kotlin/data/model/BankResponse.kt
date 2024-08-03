package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankResponse(
    @SerialName("banks")
    val banks: List<Bank>,
)