package core.designSystem.helper

import data.model.Bank

data class PhoneNumberState(
    val phoneNumber: String = "",
)

data class UsernameState(
    val username: String = "",
)

data class SearchState(
    val query: String = "",
    val filteredBanks: List<Bank> = emptyList(),
)
