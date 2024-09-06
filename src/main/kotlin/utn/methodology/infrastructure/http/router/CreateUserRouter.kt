package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import utn.methodology.infrastructure.persistence.MongoUserRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.commands.CreateUserCommand
import utn.methodology.infrastructure.http.actions.CreateUserAction
import utn.methodology.infrastructure.persistence.connectToMongoDB
import utn.methodology.application.commandhandlers.CreateUserHandler

fun Application.createUserRoutes() {
    val mongoDatabase = connectToMongoDB()

    val userMongoUserRepository = MongoUserRepository(mongoDatabase)

    val createUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository))

    routing {

        post("/users") {
            val body = call.receive<CreateUserCommand>()
            createUserAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }
}
}