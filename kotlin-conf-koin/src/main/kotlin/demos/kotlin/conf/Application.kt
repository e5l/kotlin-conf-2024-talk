package demos.kotlin.conf

import demos.kotlin.conf.plugins.configureRouting
import demos.kotlin.conf.plugins.configureSerialization
import io.ktor.client.engine.cio.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        modules(module {
            single { CIO.create() }
        })
    }

    configureSerialization()
    configureRouting()
}
