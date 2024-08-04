package data.repository

import core.network.utils.ApiResponse
import data.api.KycService
import data.model.AccountResponse
import data.model.GenericResponseModel
import data.model.SelectBankRequestModel
import domain.repository.KycRepository

class KycRepositoryImp(private val kycService: KycService) : KycRepository {
    override suspend fun selectBank(selectBankRequestModel: SelectBankRequestModel): ApiResponse<GenericResponseModel> =
        kycService.selectBank(selectBankRequestModel)

    override suspend fun setPhoneNumber(phoneNumber: String): ApiResponse<GenericResponseModel> =
        kycService.setPhoneNumber(phoneNumber)

    override suspend fun setUsername(username: String): ApiResponse<GenericResponseModel> =
        kycService.setUsername(username)

    override suspend fun setCardInfo(cardNumber: String, cardPassword: String): ApiResponse<AccountResponse> =
        kycService.setCardInfo(cardNumber, cardPassword)
}