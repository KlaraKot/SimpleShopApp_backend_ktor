package com.example.routes


import com.example.data.models.Tea
import com.example.data.models.teas
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addTea(){
    post("/post/tea"){
        val tea = call.receive<Tea>()
        teas.add(tea)
        call.respond(HttpStatusCode.Created, "Tea saved")
    }
}

fun Route.getById(){
    get("/get/tea/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val tea = teas.find{it.teaId == id} ?: return@get call.respondText(
            "no tea with id $id",
            status = HttpStatusCode.NotFound
        )
        call.respond(tea)
    }
}

fun Route.getAll(){
    get("/get/tea/all"){
        if(teas.isNotEmpty()){
            call.respond(teas)
        }
        else{
            call.respondText("no tea found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteTea(){
    delete("/delete/tea/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        if(teas.removeIf { it.teaId == id}){
            call.respondText("tea removed correctly", status = HttpStatusCode.Accepted)
        }
        else{
            call.respondText("Not found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Application.allTeaRoutes(){
    routing{
        addTea()
        getById()
        getAll()
        deleteTea()
    }
}