package presentation.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.passcode.PasscodeViewmodel
import presentation.splashScreen.SplashViewModel
import presentation.Bank.BankSelectionViewModel

val presentationModule = module {
    viewModelOf(::PasscodeViewmodel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::BankSelectionViewModel)
}