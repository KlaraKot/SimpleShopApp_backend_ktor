package com.example
import allUserRoutes
import com.example.data.models.CategoryDB
import com.example.data.models.OrderDB
import com.example.data.models.OrderDetailsDB
import com.example.data.models.ProductDB
import io.ktor.server.netty.*
import io.ktor.server.engine.embeddedServer
import com.example.plugins.*
import com.example.routes.allOrderDetailsRoutes
import com.example.routes.allOrderRoutes
import com.example.routes.allProductRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.slf4j.event.Level
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.models.UserDB


fun main(args: Array<String>) {


    Database.connect("jdbc:sqlite:./data/data.db", "org.sqlite.JDBC")


    transaction{

        SchemaUtils.drop(UserDB, CategoryDB, OrderDB, OrderDetailsDB, ProductDB)
        SchemaUtils.create(UserDB, CategoryDB, OrderDB, OrderDetailsDB, ProductDB)

        UserDB.insert {
            it[userId] = "USR1"
            it[email] = "supermax@gmail.com"
            it[name] = "Max"
            it[surname] = "Verstappen"
            it[passwordHash] = "WDC2021"
        }
        UserDB.insert {
            it[userId] = "USR2"
            it[email] = "charlec@gmail.com"
            it[name] = "Charles"
            it[surname] = "Leclerc"
            it[passwordHash] = "NW2021"
        }
        UserDB.insert {
            it[userId] = "USR3"
            it[email] = "vettel@gmail.com"
            it[name] = "Sebastian"
            it[surname] = "Vettel"
            it[passwordHash] = "WDC2007"
        }

        ProductDB.insert{
            it[productId] = "Coffee11"
            it[name] = "Illy Arabica Selection"
            it[type] = "Ziarnista"
            it[price] = 20
            it[weight] = 200
            it[quantity] = 100
            it[categoryId] = "Coffee1"

        }
        ProductDB.insert{
            it[productId] = "Coffee12"
            it[name] = "Kyoto Colombia"
            it[type] = "Ziarnista"
            it[price] = 23
            it[weight] = 200
            it[quantity] = 100
            it[categoryId] = "Coffee1"

        }
        ProductDB.insert{
            it[productId] = "Coffee13"
            it[name] = "Nescaffe"
            it[type] = "Rozpuszczalna"
            it[price] = 10
            it[weight] = 200
            it[quantity] = 100
            it[categoryId] = "Coffee1"

        }
    }



    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CallLogging){
            level = Level.DEBUG
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }

        routing {
            trace { application.log.trace(it.buildText()) }
        }


        configureRouting()
        allUserRoutes()
        allProductRoutes()
        allOrderRoutes()
        allOrderDetailsRoutes()

    }.start(wait = true)

}

