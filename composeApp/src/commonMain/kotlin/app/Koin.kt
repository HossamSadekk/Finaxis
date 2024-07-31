package app

import core.network.di.networkModule
import data.di.dataModule
import domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.di.presentationModule

fun initializeKoin() {
    startKoin {
        modules(commonModule)
    }
}

val commonModule = module {
    includes(networkModule)
    includes(dataModule)
    includes(domainModule)
    includes(presentationModule)
}