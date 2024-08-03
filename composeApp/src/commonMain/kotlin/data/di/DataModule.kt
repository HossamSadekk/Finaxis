package data.di

import data.api.BankService
import data.api.RegisterUserService
import data.api.serviceImp.BankServiceImp
import data.api.serviceImp.RegisterUserServiceImp
import data.repository.BankRepositoryImp
import data.repository.RegisterUserRepositoryImp
import data.repository.UserManagerRepositoryImp
import domain.repository.BankRepository
import domain.repository.RegisterUserRepository
import domain.repository.UserManagerRepository
import org.koin.dsl.module

val serviceModule = module {
    single<RegisterUserService> { RegisterUserServiceImp(get()) }
    single<BankService> { BankServiceImp(get()) }
}

val repositoryModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImp(get()) }
    single<UserManagerRepository> { UserManagerRepositoryImp(get()) }
    single<BankRepository> { BankRepositoryImp(get()) }
}

val dataModule = module {
    includes(serviceModule, repositoryModule)
}