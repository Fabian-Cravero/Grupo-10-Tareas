package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import utn.methodology.infrastructure.persistence.connectToMongoDB
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.commandhandlers.GetUserHandler
import utn.methodology.infrastructure.http.actions.GetUserAction
import utn.methodology.infrastructure.persistence.UserMongoRepository

fun Application.GetUserRouter(){
    val mongoDatabase = connectToMongoDB()
    val userMongoUserRepository = UserMongoRepository(mongoDatabase)
    val getUserAction = GetUserAction(GetUserHandler(userMongoUserRepository))
    routing {

        get ("/users") {

            val users = userMongoUserRepository.findAll()
            getUserAction.execute(body);

            call.respond(HttpStatusCode.Created, users.map{it.toPrimitives()})

        }
    }
}