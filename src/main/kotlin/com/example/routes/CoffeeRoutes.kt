package com.example.routes

import com.example.data.models.Coffee
import com.example.data.models.ListOfcoffee
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addCoffee(){
    post("/addCoffee"){
        val coffee = call.receive<Coffee>()
        ListOfcoffee.add(coffee)
        call.respond(HttpStatusCode.Created, "Coffee saved")
    }
}

fun Route.getCoffeeById(){
    get("/get/coffee/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val coffee = ListOfcoffee.find{it.coffeeId == id} ?: return@get call.respondText(
            "no coffee with id $id",
            status = HttpStatusCode.NotFound
        )
        call.respond(coffee)
    }
}

fun Route.getAllCoffee(){
    get("/get/coffee/all"){
        if(ListOfcoffee.isNotEmpty()){
            call.respond(ListOfcoffee)
        }
        else{
            call.respondText("no coffee found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteCoffee(){
    delete("/delete/coffee/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        if(ListOfcoffee.removeIf { it.coffeeId == id}){
            call.respondText("coffee removed correctly", status = HttpStatusCode.Accepted)
        }
        else{
            call.respondText("Not found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Application.allCoffeeRoutes(){
    routing{
        addCoffee()
        getById()
        getAllCoffee()
        deleteCoffee()
    }
}