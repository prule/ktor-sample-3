package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.server.plugins.di.DI
import io.ktor.server.plugins.di.DependencyConflictPolicy
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    class MockGreetingService: GreetingService {
        companion object {
            const val GREETING = "Mock Greeting"
        }
        override fun sayHello(): String {
            return GREETING
        }
    }

    @Test
    fun testRoot() = testApplication {
        application {
            dependencies {
                provide { MockGreetingService() as GreetingService }
            }
            module2()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(MockGreetingService.GREETING, bodyAsText())

            val message = this.bodyAsText()
            println(message)
        }
    }

}
