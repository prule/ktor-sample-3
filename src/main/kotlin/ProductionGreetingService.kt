package com.example

class ProductionGreetingService: GreetingService {
    override fun sayHello(): String {
        return "Hello World (a)"
    }
}