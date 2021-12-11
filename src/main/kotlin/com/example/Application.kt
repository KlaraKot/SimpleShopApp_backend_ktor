package com.example


import allUserRoutes
import com.example.modelsDB.UserDB
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.engine.embeddedServer
import com.example.plugins.*
import io.ktor.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.Serializable


data class UserN(
    val userId: String,
    val email: String,
    val name: String,
    val surname: String,
    val passwordHash: String
): Serializable

object UserD: Table() {
    var userIdDB: Column<String> = varchar("userIdDB", 50)
    var email: Column<String> = varchar("email", 50)
    var name: Column<String> = varchar("name", 50)
    var surname: Column<String> = varchar("surname", 50)
    var passwordHash: Column<String> = varchar("passwordHash", 50)

}







fun main() {
    fun main(args: Array<String>) {

        Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")

    }


    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        allUserRoutes()
      //  allTeaRoutes()
     //   allCoffeeRoutes()
     //   allBasketRoutes()

    }.start(wait = true)
}

