package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel
import domain.repository.KycRepository

class SelectBankUseCase(private val kycRepository: KycRepository) :
    BaseUseCase<SelectBankRequestModel, ApiResponse<GenericResponseModel>>() {
    override suspend fun execute(params: SelectBankRequestModel): ApiResponse<GenericResponseModel> =
        kycRepository.selectBank(params)
}