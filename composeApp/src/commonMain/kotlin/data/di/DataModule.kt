package data.di

import data.api.RegisterUserService
import data.api.serviceImp.RegisterUserServiceImp
import data.repository.RegisterUserRepositoryImp
import domain.repository.RegisterUserRepository
import org.koin.dsl.module



val serviceModule = module {
    single<RegisterUserService> { RegisterUserServiceImp(get()) }
}

val repositoryModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImp(get()) }
}


val dataModule = module {
    includes(serviceModule, repositoryModule)
}