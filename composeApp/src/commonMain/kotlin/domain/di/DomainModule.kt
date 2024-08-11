package domain.di

import domain.usecase.apiUseCases.CheckIfUserExistUseCase
import domain.usecase.apiUseCases.GetAccountDetailsUseCase
import domain.usecase.apiUseCases.GetBanksUseCase
import domain.usecase.apiUseCases.GetPendingRequestsUseCase
import domain.usecase.apiUseCases.LoginUserUseCase
import domain.usecase.apiUseCases.RegisterUserUseCase
import domain.usecase.apiUseCases.RequestDetailsUseCase
import domain.usecase.apiUseCases.RequestMoneyUseCase
import domain.usecase.apiUseCases.RespondToRequestUseCase
import domain.usecase.apiUseCases.SelectBankUseCase
import domain.usecase.apiUseCases.SetCardInfoKYCUseCase
import domain.usecase.apiUseCases.SetPhoneNumberKYCUseCase
import domain.usecase.apiUseCases.SetUsernameKYCUseCase
import domain.usecase.apiUseCases.TransferMoneyUseCase
import domain.usecase.localUseCases.GetUserLoggedInUseCase
import domain.usecase.localUseCases.GetUserPasscodeUseCase
import domain.usecase.localUseCases.GetUserTokenUseCase
import domain.usecase.localUseCases.GetUsernameUseCase
import domain.usecase.localUseCases.IsUserHasAccountUseCase
import domain.usecase.localUseCases.SaveUserLoggedInUseCase
import domain.usecase.localUseCases.SaveUserPasscodeUseCase
import domain.usecase.localUseCases.SaveUserTokenUseCase
import domain.usecase.localUseCases.SaveUsernameUseCase
import domain.usecase.localUseCases.SetUserHasAccountUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::RegisterUserUseCase)
    singleOf(::SaveUserLoggedInUseCase)
    singleOf(::GetUserLoggedInUseCase)
    singleOf(::SaveUserTokenUseCase)
    singleOf(::GetUserTokenUseCase)
    singleOf(::GetBanksUseCase)
    singleOf(::SelectBankUseCase)
    singleOf(::SetPhoneNumberKYCUseCase)
    singleOf(::SetUsernameKYCUseCase)
    singleOf(::SetCardInfoKYCUseCase)
    singleOf(::SetUserHasAccountUseCase)
    singleOf(::IsUserHasAccountUseCase)
    singleOf(::SaveUserPasscodeUseCase)
    singleOf(::GetUserPasscodeUseCase)
    singleOf(::LoginUserUseCase)
    singleOf(::GetAccountDetailsUseCase)
    singleOf(::CheckIfUserExistUseCase)
    singleOf(::RequestDetailsUseCase)
    singleOf(::TransferMoneyUseCase)
    singleOf(::RequestMoneyUseCase)
    singleOf(::SaveUsernameUseCase)
    singleOf(::GetUsernameUseCase)
    singleOf(::GetPendingRequestsUseCase)
    singleOf(::RespondToRequestUseCase)
}