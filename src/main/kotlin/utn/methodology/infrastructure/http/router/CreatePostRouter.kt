package utn.methodology.infrastructure.http.router
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.commandhandlers.CreatePostHandler
import utn.methodology.application.commands.CreatePostCommand
import utn.methodology.infrastructure.http.actions.CreatePostAction
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB

fun Application.CreatePostRouter(){
    val MongoDB = connectToMongoDB()

    val postMongoRepository = PostMongoRepository(MongoDB)

    val createPostAction = CreatePostAction(CreatePostHandler(postMongoRepository))

    routing {
        post("/posts") {
            val body = call.receive<CreatePostCommand>()
            createPostAction.execute(body);
            call.respond(HttpStatusCode.Created, "message" to "ok")
        }
    }
}
