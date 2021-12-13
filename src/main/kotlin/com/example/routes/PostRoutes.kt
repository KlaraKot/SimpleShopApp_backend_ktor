package com.example.routes

import com.example.controllers.PostController
import io.ktor.application.*
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Post(){
    get("/post"){
        val postController = PostController()
        call.respond(postController.addSomeUsers())
    }
}

fun Application.allPostRoutes(){
    routing{
        Post()
    }
}