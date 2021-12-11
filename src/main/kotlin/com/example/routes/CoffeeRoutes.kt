package com.example.routes
import com.example.controllers.coffeeController
import com.example.controllers.teaController
import com.example.data.models.Coffee
import com.example.data.models.Tea
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*



    fun Route.coffeeByName(){
        val coffeeController = coffeeController()
        get("/get/coffee/{name}"){
            val name = call.parameters["name"] ?: return@get call.respondText(
                "Missing or malformed name",
                status = HttpStatusCode.BadRequest
            )
            var list: ArrayList<Coffee> = arrayListOf()
            list = coffeeController.getByName(name)
            call.respond(list)
        }
    }

    fun Route.coffeByType(){
        val coffeeController = coffeeController()
        get("/get/coffee/{type}"){
            val type = call.parameters["type"] ?: return@get call.respondText(
                "Missing or malformed type",
                status = HttpStatusCode.BadRequest
            )
            var list: ArrayList<Coffee> = arrayListOf()
            list = coffeeController.getByType(type)
            call.respond(list)
        }
    }


    fun Route.coffeeAll(){
        val coffeeController = coffeeController()
        get("/get/coffee/all"){
            var list: ArrayList<Coffee> = arrayListOf()
            list = coffeeController.getAll()
            call.respond(list)
        }
    }



    fun Application.allCoffeeRoutes(){
        routing{
            coffeeByName()
            coffeByType()
            coffeeAll()
        }
}