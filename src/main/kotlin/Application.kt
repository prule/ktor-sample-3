package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.di.dependencies

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    dependencies {
        provide {
            ProductionGreetingService() as GreetingService
        }
    }
    module2()
}

fun Application.module2() {
    configureHTTP()
    configureSecurity()
    configureFrameworks()
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureRouting()
}
