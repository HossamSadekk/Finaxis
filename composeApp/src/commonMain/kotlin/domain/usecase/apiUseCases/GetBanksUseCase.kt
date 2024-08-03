package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCaseNoParam
import core.network.utils.ApiResponse
import data.model.BankResponse
import domain.repository.BankRepository

class GetBanksUseCase(private val bankRepository: BankRepository) : BaseUseCaseNoParam<ApiResponse<BankResponse>>() {
    override suspend fun execute(): ApiResponse<BankResponse> = bankRepository.getBanks()
}