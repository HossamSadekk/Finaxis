package presentation.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.passcode.PasscodeViewmodel

val presentationModule = module {
    viewModelOf(::PasscodeViewmodel)
}