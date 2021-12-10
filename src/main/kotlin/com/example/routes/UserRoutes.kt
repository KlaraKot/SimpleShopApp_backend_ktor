import com.example.data.models.User
import com.example.data.models.users
import com.example.modelsDB.UserDB
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.insert

fun Route.addUser(){
   /* Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    post("/addUser"){
        val user = call.receive<User>()

        transaction{
            UserDB.insert{
                it[]
            }
        }

        call.respond(HttpStatusCode.Created, "User saved")
    }*/
}

fun Route.getById(){
    get("/user/get/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val user = users.find{it.userId == id} ?: return@get call.respondText(
            "no user with id $id",
            status = HttpStatusCode.NotFound
        )
        call.respond(user)
    }
}

fun Route.getAll(){
    get("/user/get/all"){



    }

}

fun Route.deleteUser(){
    delete("/user/delete/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        if(users.removeIf { it.userId == id}){
            call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
        }
        else{
            call.respondText("Not found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Application.allUserRoutes(){
    routing{
        addUser()
        getById()
        getAll()
        deleteUser()
    }
}
