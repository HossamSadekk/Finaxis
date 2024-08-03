package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.GenericResponseModel
import domain.repository.KycRepository

class SetPhoneNumberKYCUseCase(private val kycRepository: KycRepository) :
    BaseUseCase<String, ApiResponse<GenericResponseModel>>() {
    override suspend fun execute(params: String): ApiResponse<GenericResponseModel> =
        kycRepository.setPhoneNumber(params)
}