package com.example


import allUserRoutes
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routes.allBasketRoutes
import com.example.routes.allCoffeeRoutes
import com.example.routes.allTeaRoutes
import org.jetbrains.exposed.sql.*

fun main() {

    Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        allUserRoutes()
        allTeaRoutes()
        allCoffeeRoutes()
        allBasketRoutes()

    }.start(wait = true)
}

