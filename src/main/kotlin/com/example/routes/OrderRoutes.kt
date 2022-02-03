package com.example.routes

import com.example.data.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.addOrder(){
    post("/addNewOrder"){
        val order = call.receive<Order>()
        transaction{
            OrderDB.insert{
                it[orderId] = order.orderId
                it[userId] = order.userId
            }
        }
        call.respond(HttpStatusCode.Created, "Basket saved")
    }
}

fun Route.getOrderById(){
    get("/get/order/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val response = transaction {
            OrderDB.select {OrderDB.orderId eq id}.map{Order.fromRow(it)}
        }
        call.respond(response)
    }
}


fun Application.allOrderRoutes(){
    routing{
        addOrder()
        getOrderById()
    }
}