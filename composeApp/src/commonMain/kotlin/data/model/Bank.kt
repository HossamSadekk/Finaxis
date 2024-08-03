package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("iban")
    val iban: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("isSelected")
    var isSelected: Boolean = false,
)