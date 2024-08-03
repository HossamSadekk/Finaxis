package data.repository

import core.network.utils.ApiResponse
import data.api.BankService
import data.model.BankResponse
import domain.repository.BankRepository

class BankRepositoryImp(private val bankService: BankService) : BankRepository {
    override suspend fun getBanks(): ApiResponse<BankResponse> =
        bankService.getBanks()
}