package data.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectBankRequestModel(
    val bankId: Int,
)