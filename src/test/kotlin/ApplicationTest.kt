package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.plugins.di.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    class MockGreetingService : GreetingService {
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
            // provide mocks
            dependencies {
                provide { Service1(MockSampleRepository1()) }
                provide { MockGreetingService() as GreetingService }
            }
            // now load the module that depends on them
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
