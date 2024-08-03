package data.api

import core.network.utils.ApiResponse
import data.model.BankResponse

interface BankService {
    suspend fun getBanks(): ApiResponse<BankResponse>
}