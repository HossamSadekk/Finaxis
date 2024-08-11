package core.network.utils

object Rout {
    const val BASE_URL = "http://10.0.2.2:8080/"
    const val TIME_OUT = 60_000L
}

object Endpoints {
    // AUTH
    const val REGISTER = "auth/register"
    const val LOGIN = "auth/login"
    // BANK
    const val BANK = "api/banks"
    // KYC
    const val KYC_SELECT_BANK = "api/kyc/select-bank"
    const val KYC_PHONE_NUMBER = "api/kyc/phone-number"
    const val KYC_USERNAME = "api/kyc/submit-username"
    const val KYC_CARD_INFO = "api/kyc/submit-card"
    // ACCOUNT
    const val ACCOUNT_DETAILS = "api/me"
    const val CHECK_USERNAME = "api/check-username"
    const val REQUEST_DETAILS = "api/request-details"
    const val TRANSFER_MONEY = "api/transaction/transfer"
    const val REQUEST_MONEY = "api/transaction/request"

}

object Parameters {
    const val PHONE_NUMBER = "phoneNumber"
    const val USERNAME = "username"
    const val CARD_NUMBER = "cardNumber"
    const val CARD_PASS = "cardPassword"
    const val SENDER = "senderUsername"
    const val RECEIVER = "receiverUsername"
    const val AMOUNT = "amount"
    const val NOTE = "note"
}