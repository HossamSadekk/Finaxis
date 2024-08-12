package domain.repository

import core.network.utils.ApiResponse
import data.model.AccountDetailsResponse
import data.model.RequestDetailsResponse
import data.model.Transaction
import data.model.TransactionResponseModel

interface AccountRepository {
    suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse>
    suspend fun checkIfUsernameExist(username: String): ApiResponse<Boolean>
    suspend fun requestDetails(username: String): ApiResponse<RequestDetailsResponse>
    suspend fun transferMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel>

    suspend fun requestMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel>

    suspend fun getPendingRequestTransactions(username: String): ApiResponse<List<TransactionResponseModel>>
    suspend fun respondToMoneyRequest(
        transactionId: String,
        accept: Boolean,
    ): ApiResponse<TransactionResponseModel>

    suspend fun getTransactionList(accountId: Int): ApiResponse<List<Transaction>>

}