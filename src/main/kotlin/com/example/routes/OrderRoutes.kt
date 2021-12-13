package com.example.routes

import com.example.controllers.OrderController
import com.example.data.models.*
import com.example.modelsDB.OrderDB
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addOrder(){
    post("/addNewOrder"){
        val orderController = OrderController()
        val order = call.receive<Order>()
        orderController.addNewOrder(order)
        call.respond(HttpStatusCode.Created, "Basket saved")
    }
}

fun Route.getOrderById(){
    get("/get/order/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val orderController = OrderController()
        call.respond(orderController.getOrderById(id))
    }
}


fun Application.allBasketRoutes(){
    routing{
        addOrder()
        getOrderById()
    }
}