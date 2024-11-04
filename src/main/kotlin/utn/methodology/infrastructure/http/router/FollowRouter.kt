// REVISAR TEMA DE LLAMADA A FollowRouter y que funcione

package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.commandhandlers.FollowUserHandler
import utn.methodology.application.commandhandlers.unFollowUserHandler
import utn.methodology.application.commands.FollowUserCommand
import utn.methodology.application.commands.unFollowUserCommand
import utn.methodology.infrastructure.http.actions.FollowUserAction
import utn.methodology.infrastructure.http.actions.unFollowUserAction
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.FollowMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB

fun Application.FollowRouter() {
    val mongoDatabase = connectToMongoDB()
    val followRepository = FollowMongoRepository(mongoDatabase)
    val postRepository = PostMongoRepository(mongoDatabase)

    // seguir y dejar de seguir usuario
    val followUserAction = FollowUserAction(FollowUserHandler(followRepository))
    val unFollowUserAction = unFollowUserAction(unFollowUserHandler(followRepository))

    routing {
        // obtener los posts de un usuario especifico
        get("/posts/user/{userId}") {
            val userId = call.parameters["userId"]
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "User ID is missing")
                return@get
            }

            // obtener usuarios que el usuario actual sigue
            val followedUsers = followRepository.findByUser(userId)?.following ?: emptyList()

            // obtener los posts y filtrar por usuarios seguidos
            val posts = postRepository.findAll()
                .filter { it.userId in followedUsers }
                .sortedByDescending { it.createdAt }

            // responde con la lista de usuarios ya filtrada
            call.respond(posts)
        }

        // ruta follow
        post("/follow") {
            val body = call.receive<FollowUserCommand>()
            followUserAction.execute(body)
            call.respond(HttpStatusCode.Created, mapOf("message" to "User followed successfully"))
        }

        // ruta unfollow
        delete("/unfollow") {
            val body = call.receive<unFollowUserCommand>()
            val userExists = followRepository.findByUser(body.username) != null

            // dejar de seguir si user existe
            if (userExists) {
                unFollowUserAction.execute(body)
                call.respond(HttpStatusCode.OK, mapOf("message" to "User unfollowed successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, "User not found")
            }
        }
    }
}