package data.di

import data.api.BankService
import data.api.KycService
import data.api.RegisterUserService
import data.api.serviceImp.BankServiceImp
import data.api.serviceImp.KycServiceImp
import data.api.serviceImp.RegisterUserServiceImp
import data.repository.BankRepositoryImp
import data.repository.KycRepositoryImp
import data.repository.RegisterUserRepositoryImp
import data.repository.UserManagerRepositoryImp
import domain.repository.BankRepository
import domain.repository.KycRepository
import domain.repository.RegisterUserRepository
import domain.repository.UserManagerRepository
import org.koin.dsl.module

val serviceModule = module {
    single<RegisterUserService> { RegisterUserServiceImp(get()) }
    single<BankService> { BankServiceImp(get()) }
    single<KycService> { KycServiceImp(get()) }
}

val repositoryModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImp(get()) }
    single<UserManagerRepository> { UserManagerRepositoryImp(get()) }
    single<BankRepository> { BankRepositoryImp(get()) }
    single<KycRepository> { KycRepositoryImp(get()) }
}

val dataModule = module {
    includes(serviceModule, repositoryModule)
}