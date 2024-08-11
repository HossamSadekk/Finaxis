package presentation.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.MoneyRequests.RequestsScreenViewModel
import presentation.bank.BankSelectionViewModel
import presentation.home.HomeScreenViewModel
import presentation.kyc_cardInfo.CardInfoKycViewModel
import presentation.kyc_phoneNumber.PhoneNumberKycViewModel
import presentation.kyc_username.KycUsernameViewModel
import presentation.passcode.PasscodeViewmodel
import presentation.send_receive_money.MoneyRequestViewModel
import presentation.splashScreen.SplashViewModel
import presentation.username_money_request.RequestScreenViewModel

val presentationModule = module {
    viewModelOf(::PasscodeViewmodel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::BankSelectionViewModel)
    viewModelOf(::PhoneNumberKycViewModel)
    viewModelOf(::KycUsernameViewModel)
    viewModelOf(::CardInfoKycViewModel)
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::RequestScreenViewModel)
    viewModelOf(::MoneyRequestViewModel)
    viewModelOf(::RequestsScreenViewModel)
}