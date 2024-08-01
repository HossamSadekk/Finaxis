package core.local.di

import core.sharedPlatform.createDataStore
import org.koin.dsl.module

val localModule = module {
    single { createDataStore() }
}