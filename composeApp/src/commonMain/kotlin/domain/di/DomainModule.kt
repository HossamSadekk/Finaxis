package domain.di

import domain.usecase.localUseCases.GetUserLoggedInUseCase
import domain.usecase.apiUseCases.RegisterUserUseCase
import domain.usecase.localUseCases.SaveUserLoggedInUseCase
import domain.usecase.localUseCases.SaveUserTokenUseCase
import domain.usecase.localUseCases.GetUserTokenUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::RegisterUserUseCase)
    singleOf(::SaveUserLoggedInUseCase)
    singleOf(::GetUserLoggedInUseCase)
    singleOf(::SaveUserTokenUseCase)
    singleOf(::GetUserTokenUseCase)
}