package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB
import utn.methodology.application.commands.DeletePostCommand
import utn.methodology.application.commandhandlers.DeletePostHandler
import utn.methodology.infrastructure.http.actions.DeletePostAction

fun Application.userRouter() {
    val mongoDatabase = connectToMongoDB()
    val PostMongoRepository = PostMongoRepository(mongoDatabase)
    val deletePostAction = DeletePostAction(DeletePostHandler(PostMongoRepository))

    routing {
        delete("/posts/{id}") {

            val query = DeletePostCommand(call.parameters["id"].toString())

            deletePostAction.execute(query)

            call.respond(HttpStatusCode.NoContent)
        }
    }
}
