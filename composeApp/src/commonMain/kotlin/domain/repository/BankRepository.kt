package domain.repository

import core.network.utils.ApiResponse
import data.model.BankResponse

interface BankRepository {
    suspend fun getBanks(): ApiResponse<BankResponse>
}