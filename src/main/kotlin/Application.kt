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
}

fun Application.module2() {

    // Implementation is specified in application.yaml
    //    ktor:
    //      application:
    //        dependencies:
    //          - com.example.SampleRepository1
    //          - com.example.Service1
//    val sampleRepository: SampleRepository by dependencies
//    println("sampleRepository is ${sampleRepository.javaClass.simpleName}")


    // service1 gets automatically injected with its repository via dependencies
    val service1: Service1 by dependencies
    println("Service1.sampleRepository is ${service1.repository.javaClass.simpleName}")


    configureHTTP()
    configureSecurity()
    configureFrameworks()
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureRouting()
}
