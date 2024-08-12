package data.repository

import core.network.utils.ApiResponse
import data.api.AccountUserService
import data.model.AccountDetailsResponse
import data.model.RequestDetailsResponse
import data.model.Transaction
import data.model.TransactionResponseModel
import domain.repository.AccountRepository

class AccountRepositoryImp(private val accountUserService: AccountUserService) : AccountRepository {
    override suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse> =
        accountUserService.getAccountDetails()

    override suspend fun checkIfUsernameExist(username: String): ApiResponse<Boolean> =
        accountUserService.checkIfUsernameExist(username)

    override suspend fun requestDetails(username: String): ApiResponse<RequestDetailsResponse> =
        accountUserService.requestDetails(username)

    override suspend fun transferMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel> =
        accountUserService.transferMoney(senderUsername, receiverUsername, amount, note)

    override suspend fun requestMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel> =
        accountUserService.requestMoney(senderUsername, receiverUsername, amount, note)

    override suspend fun getPendingRequestTransactions(username: String): ApiResponse<List<TransactionResponseModel>> =
        accountUserService.getPendingRequestTransactions(username)

    override suspend fun respondToMoneyRequest(
        transactionId: String,
        accept: Boolean,
    ): ApiResponse<TransactionResponseModel> = accountUserService.respondToMoneyRequest(transactionId, accept)

    override suspend fun getTransactionList(accountId: Int): ApiResponse<List<Transaction>> =
        accountUserService.getTransactionList(accountId)
}