import com.example.data.models.User
import com.example.data.models.users
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.addUser(){
    post("/addUser"){
        val user = call.receive<User>()
        users.add(user)
        call.respond(HttpStatusCode.Created, "User saved")
    }
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
        if(users.isNotEmpty()){
            call.respond(users)
        }
        else{
            call.respondText("no customers found", status = HttpStatusCode.NotFound)
        }
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
