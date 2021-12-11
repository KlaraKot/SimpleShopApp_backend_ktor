package com.example.routes/*package com.example.routes



import com.example.data.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addBasket(){
    post("/addBasket"){
        val basket = call.receive<Basket>()
        baskets.add(basket)
        call.respond(HttpStatusCode.Created, "Basket saved")
    }
}

fun Route.getBasketById(){
    get("/get/basket/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val basket = baskets.find{it.basketId == id} ?: return@get call.respondText(
            "no basket with id $id",
            status = HttpStatusCode.NotFound
        )
        call.respond(basket)
    }
}

fun Route.getAllBaskets(){
    get("/get/basket/all"){
        if(baskets.isNotEmpty()){
            call.respond(baskets)
        }
        else{
            call.respondText("no basket found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteBasket(){
    delete("/delete/basket/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        if(baskets.removeIf { it.basketId == id}){
            call.respondText("basket removed correctly", status = HttpStatusCode.Accepted)
        }
        else{
            call.respondText("Not found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Application.allBasketRoutes(){
    routing{
        addBasket()
        getBasketById()
        getAllBaskets()
        deleteBasket()
    }
}*/