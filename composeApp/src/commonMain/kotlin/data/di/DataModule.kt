package data.di

import data.api.AccountUserService
import data.api.BankService
import data.api.KycService
import data.api.LoginUserService
import data.api.RegisterUserService
import data.api.serviceImp.AccountUserServiceImp
import data.api.serviceImp.BankServiceImp
import data.api.serviceImp.KycServiceImp
import data.api.serviceImp.LoginServiceImp
import data.api.serviceImp.RegisterUserServiceImp
import data.repository.AccountRepositoryImp
import data.repository.BankRepositoryImp
import data.repository.KycRepositoryImp
import data.repository.LoginRepositoryImp
import data.repository.RegisterUserRepositoryImp
import data.repository.UserManagerRepositoryImp
import domain.repository.AccountRepository
import domain.repository.BankRepository
import domain.repository.KycRepository
import domain.repository.LoginRepository
import domain.repository.RegisterUserRepository
import domain.repository.UserManagerRepository
import org.koin.dsl.module

val serviceModule = module {
    single<RegisterUserService> { RegisterUserServiceImp(get()) }
    single<BankService> { BankServiceImp(get()) }
    single<KycService> { KycServiceImp(get()) }
    single<LoginUserService> { LoginServiceImp(get()) }
    single<AccountUserService> { AccountUserServiceImp(get()) }
}

val repositoryModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImp(get()) }
    single<UserManagerRepository> { UserManagerRepositoryImp(get()) }
    single<BankRepository> { BankRepositoryImp(get()) }
    single<KycRepository> { KycRepositoryImp(get()) }
    single<LoginRepository> { LoginRepositoryImp(get()) }
    single<AccountRepository> { AccountRepositoryImp(get()) }
}

val dataModule = module {
    includes(serviceModule, repositoryModule)
}