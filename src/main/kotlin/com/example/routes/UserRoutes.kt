import com.example.controllers.UserController
import com.example.data.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.addUser(){
    val userController = UserController()
    post("/addUser"){
        val user = call.receive<User>()
        userController.addUser(user)
        call.respond(HttpStatusCode.Created, "User saved")
    }
}

fun Route.getById(){//tu jeszcze naprawic
    val userController = UserController()
    get("/user/get/{id}"){
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        call.respond(userController.getById(id))
    }
}

fun Route.getAll(){
    val userController = UserController()
    get("/user/all"){
        call.respond(userController.getAll())
    }

}

fun Route.deleteUser(){
    val userController = UserController()
    delete("/user/delete/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        userController.delete(id)
        call.respond(HttpStatusCode.OK)

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
