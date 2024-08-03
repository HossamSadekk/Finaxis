package presentation.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.bank.BankSelectionViewModel
import presentation.kyc_phoneNumber.PhoneNumberKycViewModel
import presentation.passcode.PasscodeViewmodel
import presentation.splashScreen.SplashViewModel

val presentationModule = module {
    viewModelOf(::PasscodeViewmodel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::BankSelectionViewModel)
    viewModelOf(::PhoneNumberKycViewModel)
}