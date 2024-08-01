package data.di

import data.api.RegisterUserService
import data.api.serviceImp.RegisterUserServiceImp
import data.repository.RegisterUserRepositoryImp
import data.repository.UserManagerRepositoryImp
import domain.repository.RegisterUserRepository
import domain.repository.UserManagerRepository
import org.koin.dsl.module

val serviceModule = module {
    single<RegisterUserService> { RegisterUserServiceImp(get()) }
}

val repositoryModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImp(get()) }
    single<UserManagerRepository> { UserManagerRepositoryImp(get()) }
}

val dataModule = module {
    includes(serviceModule, repositoryModule)
}