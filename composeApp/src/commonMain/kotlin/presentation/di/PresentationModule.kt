package presentation.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.home.HomeScreenViewModel
import presentation.bank.BankSelectionViewModel
import presentation.kyc_cardInfo.CardInfoKycViewModel
import presentation.kyc_phoneNumber.PhoneNumberKycViewModel
import presentation.kyc_username.KycUsernameViewModel
import presentation.passcode.PasscodeViewmodel
import presentation.splashScreen.SplashViewModel

val presentationModule = module {
    viewModelOf(::PasscodeViewmodel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::BankSelectionViewModel)
    viewModelOf(::PhoneNumberKycViewModel)
    viewModelOf(::KycUsernameViewModel)
    viewModelOf(::CardInfoKycViewModel)
    viewModelOf(::HomeScreenViewModel)
}