package com.example.routes

import com.example.controllers.OrderDetailsController
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getById(){
    val orderDetailsController = OrderDetailsController()
    get("/get/OrderDetails/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        call.respond(orderDetailsController.getOrderById(id))
    }
}

fun Application.allOrderDetailsRoutes(){
    routing{
        getById()
    }
}