package data.api.serviceImp

import core.network.ktorHelpers.getRequest
import core.network.ktorHelpers.postRequest
import core.network.utils.ApiResponse
import core.network.utils.Endpoints.ACCOUNT_DETAILS
import core.network.utils.Endpoints.CHECK_USERNAME
import core.network.utils.Endpoints.COMPLETED_TRANSACTION
import core.network.utils.Endpoints.PENDING_REQUESTS
import core.network.utils.Endpoints.REQUEST_DETAILS
import core.network.utils.Endpoints.REQUEST_MONEY
import core.network.utils.Endpoints.RESPOND_REQUEST
import core.network.utils.Endpoints.TRANSFER_MONEY
import core.network.utils.Parameters.ACCEPT
import core.network.utils.Parameters.AMOUNT
import core.network.utils.Parameters.ID
import core.network.utils.Parameters.NOTE
import core.network.utils.Parameters.RECEIVER
import core.network.utils.Parameters.SENDER
import core.network.utils.Parameters.TRANSACTION_ID
import core.network.utils.Parameters.USERNAME
import data.api.AccountUserService
import data.model.AccountDetailsResponse
import data.model.RequestDetailsResponse
import data.model.Transaction
import data.model.TransactionResponseModel
import io.ktor.client.HttpClient

class AccountUserServiceImp(private val httpClient: HttpClient) : AccountUserService {
    override suspend fun getAccountDetails(): ApiResponse<AccountDetailsResponse> =
        httpClient.getRequest(path = ACCOUNT_DETAILS)

    override suspend fun checkIfUsernameExist(username: String): ApiResponse<Boolean> =
        httpClient.getRequest(path = CHECK_USERNAME, queryParams = mapOf(USERNAME to username))

    override suspend fun requestDetails(username: String): ApiResponse<RequestDetailsResponse> =
        httpClient.getRequest(path = REQUEST_DETAILS, queryParams = mapOf(USERNAME to username))

    override suspend fun transferMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel> =
        httpClient.postRequest(
            path = TRANSFER_MONEY, queryParams = mapOf(
                SENDER to senderUsername, RECEIVER to receiverUsername,
                AMOUNT to amount.toString(), NOTE to note
            )
        )

    override suspend fun requestMoney(
        senderUsername: String,
        receiverUsername: String,
        amount: Double,
        note: String,
    ): ApiResponse<TransactionResponseModel> =
        httpClient.postRequest(
            path = REQUEST_MONEY, queryParams = mapOf(
                SENDER to senderUsername, RECEIVER to receiverUsername,
                AMOUNT to amount.toString(), NOTE to note
            )
        )

    override suspend fun getPendingRequestTransactions(username: String): ApiResponse<List<TransactionResponseModel>> =
        httpClient.getRequest(path = PENDING_REQUESTS, queryParams = mapOf(USERNAME to username))

    override suspend fun respondToMoneyRequest(
        transactionId: String,
        accept: Boolean,
    ): ApiResponse<TransactionResponseModel> =
        httpClient.postRequest(
            path = RESPOND_REQUEST,
            queryParams = mapOf(TRANSACTION_ID to transactionId, ACCEPT to accept.toString())
        )

    override suspend fun getTransactionList(accountId: Int): ApiResponse<List<Transaction>> =
        httpClient.getRequest(path = COMPLETED_TRANSACTION, queryParams = mapOf(ID to accountId.toString()))
}