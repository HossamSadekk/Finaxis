package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.base.usecase.TwoStringParams
import core.network.utils.ApiResponse
import data.model.AccountResponse
import domain.repository.KycRepository

class SetCardInfoKYCUseCase(private val kycRepository: KycRepository) :
    BaseUseCase<TwoStringParams, ApiResponse<AccountResponse>>() {
    override suspend fun execute(params: TwoStringParams): ApiResponse<AccountResponse> =
        kycRepository.setCardInfo(params.firstString, params.secondString)
}