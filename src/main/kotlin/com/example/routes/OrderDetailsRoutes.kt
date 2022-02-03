package com.example.routes

import com.example.data.models.OrderDetails
import com.example.data.models.OrderDetailsDB
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.getById(){
    get("/get/OrderDetails/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val response = transaction{
            OrderDetailsDB.select{OrderDetailsDB.orderId eq id}.map{OrderDetails.fromRow(it)}
        }
        call.respond(response)
    }
}

fun Application.allOrderDetailsRoutes(){
    routing{
        getById()
    }
}