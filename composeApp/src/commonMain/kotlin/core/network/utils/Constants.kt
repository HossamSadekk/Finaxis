package core.network.utils

object Rout {
    const val BASE_URL = "http://10.0.2.2:8080/"
    const val TIME_OUT = 60_000L
}

object Endpoints {
    const val REGISTER = "auth/register"
    const val BANK = "api/banks"
    const val KYC_SELECT_BANK = "api/kyc/select-bank"
    const val KYC_PHONE_NUMBER = "api/kyc/phone-number"
    const val KYC_USERNAME = "api/kyc/submit-username"
    const val KYC_CARD_INFO = "api/kyc/submit-card"
}

object Parameters {
    const val PHONE_NUMBER = "phoneNumber"
    const val USERNAME = "username"
    const val CARD_NUMBER = "cardNumber"
    const val CARD_PASS = "cardPassword"
}