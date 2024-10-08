package core.network.di

import core.network.ktorHelpers.KtorLogger
import core.network.ktorHelpers.addTokenInterceptor
import core.network.utils.Rout.TIME_OUT
import core.sharedPlatform.NetworkConfig
import domain.repository.UserManagerRepository
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        val userManagerRepository: UserManagerRepository by inject<UserManagerRepository>()
        HttpClient() {
            install(Logging) {
                logger = KtorLogger()
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(NetworkConfig.baseUrl)
                header(HttpHeaders.ContentType, ContentType.Application.Json)

            }
            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }
            install(ContentNegotiation) {
                json(Json {
                    encodeDefaults = false
                    explicitNulls = false
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
            }
            addTokenInterceptor { userManagerRepository.getUserToken() }
        }.also { Napier.base(DebugAntilog()) }
    }
}