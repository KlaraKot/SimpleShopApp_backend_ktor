import com.example.models.User
import com.example.models.UserDB
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun Route.addUser(){
    post("/addUser"){
        val user = call.receive<User>()
        transaction{
            UserDB.insert{
                it[userId] = user.userId
                it[email] = user.email
                it[name] = user.name
                it[surname] = user.surname
                it[passwordHash] = user.passwordHash
            }
        }
        call.respond(HttpStatusCode.Created, "User saved")
    }
}

fun Route.getById(){

    get("/user/get/{id}"){
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
        val user = transaction{
            UserDB.select {UserDB.userId eq id}.map { User.fromRow(it) }
        }
        call.respond(user)
    }
}

fun Route.getAll(){
    get("/user/all/"){
        val response = transaction {
            UserDB.selectAll().map{User.fromRow(it)}
        }

        call.respond(response)
    }
}

fun Route.deleteUser(){
    delete("/user/delete/{id}"){
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val success = transaction {
            UserDB.deleteWhere { UserDB.userId eq id }
        }
        call.respond(HttpStatusCode.OK)

    }
}

fun Route.changePassword(){
    put("/user/update/{id}/{np}"){
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val np = call.parameters["np"]  ?: return@put call.respond(HttpStatusCode.BadRequest)

        val change = transaction{
            UserDB.update ({ UserDB.userId eq id }) {
                it[UserDB.passwordHash] = np
            }
        }
        call.respond(HttpStatusCode.OK)
    }
}

fun Application.allUserRoutes(){
    routing{
        addUser()
        getById()
        getAll()
        changePassword()
        deleteUser()
    }
}
