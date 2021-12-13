package com.example.routes
import com.example.controllers.ProductController
import com.example.data.models.Product
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*



fun Route.getByName(){
    val ProductController = ProductController()
    get("/get/tea/{name}"){
        val name = call.parameters["name"] ?: return@get call.respondText(
            "Missing or malformed name",
            status = HttpStatusCode.BadRequest
        )
        var list: ArrayList<Product> = arrayListOf()
        list = ProductController.getByName(name)
        call.respond(list)
    }
}

fun Route.getByType(){
    val ProductController = ProductController()
    get("/get/tea/{type}"){
        val type = call.parameters["type"] ?: return@get call.respondText(
            "Missing or malformed type",
            status = HttpStatusCode.BadRequest
        )
        var list: ArrayList<Product> = arrayListOf()
        list = ProductController.getByType(type)
        call.respond(list)
    }
}


fun Route.getAll(){
    val ProductController = ProductController()
    get("/get/tea/all"){
        var list: ArrayList<Product> = arrayListOf()
        list = ProductController.getAll()
        call.respond(list)
    }
}



fun Application.allTeaRoutes(){
    routing{
        getByName()
        getByType()
        getAll()
    }
}