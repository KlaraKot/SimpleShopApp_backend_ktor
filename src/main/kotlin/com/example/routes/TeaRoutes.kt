package com.example.routes
import com.example.controllers.teaController
import com.example.data.models.Tea
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*



fun Route.getByName(){
    val teaController = teaController()
    get("/get/tea/{name}"){
        val name = call.parameters["name"] ?: return@get call.respondText(
            "Missing or malformed name",
            status = HttpStatusCode.BadRequest
        )
        var list: ArrayList<Tea> = arrayListOf()
        list = teaController.getByName(name)
        call.respond(list)
    }
}

fun Route.getByType(){
    val teaController = teaController()
    get("/get/tea/{type}"){
        val type = call.parameters["type"] ?: return@get call.respondText(
            "Missing or malformed type",
            status = HttpStatusCode.BadRequest
        )
        var list: ArrayList<Tea> = arrayListOf()
        list = teaController.getByType(type)
        call.respond(list)
    }
}


fun Route.getAll(){
    val teaController = teaController()
    get("/get/tea/all"){
        var list: ArrayList<Tea> = arrayListOf()
        list = teaController.getAll()
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