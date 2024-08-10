package domain.usecase.apiUseCases

import core.base.usecase.BaseUseCase
import core.network.utils.ApiResponse
import data.model.RequestDetailsResponse
import domain.repository.AccountRepository

class RequestDetailsUseCase(private val accountRepository: AccountRepository):BaseUseCase<String,ApiResponse<RequestDetailsResponse>>() {
    override suspend fun execute(params: String): ApiResponse<RequestDetailsResponse> =
        accountRepository.requestDetails(params)
}