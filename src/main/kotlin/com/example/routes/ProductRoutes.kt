package com.example.routes
import com.example.data.models.Product
import com.example.data.models.ProductDB
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun Route.getByName(){

    get("/get/tea/{name}"){
        val name = call.parameters["name"] ?: return@get call.respondText(
            "Missing or malformed name",
            status = HttpStatusCode.BadRequest
        )
        var list = transaction {
            ProductDB.select(ProductDB.name eq name).map{Product.fromRow(it)}
        }
        call.respond(list)
    }
}

fun Route.getByType(){
    get("/get/tea/{type}"){
        val type = call.parameters["type"] ?: return@get call.respondText(
            "Missing or malformed type",
            status = HttpStatusCode.BadRequest
        )
        var list = transaction{
            ProductDB.select(ProductDB.name eq type).map{Product.fromRow(it)}
        }
        call.respond(list)
    }
}


fun Route.getAll(){
    get("/get/tea/all"){
        var list = transaction{
            ProductDB.selectAll().map{Product.fromRow(it)}
        }
        call.respond(list)
    }
}



fun Application.allProductRoutes(){
    routing{
        getByName()
        getByType()
        getAll()
    }
}