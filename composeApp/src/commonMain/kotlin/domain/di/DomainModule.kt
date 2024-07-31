package domain.di

import domain.usecase.RegisterUserUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::RegisterUserUseCase)
}