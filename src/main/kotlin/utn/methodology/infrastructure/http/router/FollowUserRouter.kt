package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import utn.methodology.application.commandhandlers.unFollowUserHandler
import utn.methodology.application.commands.FollowUserCommand
import utn.methodology.application.commands.unFollowUserCommand
import utn.methodology.infrastructure.http.actions.unFollowUserAction
import utn.methodology.infrastructure.persistence.FollowMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB
import utn.methodology.infrastructure.http.actions.FollowUserAction
import utn.methodology.application.commandhandlers.FollowUserHandler
import utn.methodology.domain.entities.Post
import utn.methodology.infrastructure.persistence.PostMongoRepository


fun Application.FollowUserRouter(){
    val mongoDatabase = connectToMongoDB()
    val followMongoUserRepository = FollowMongoRepository(mongoDatabase)
    val postRepository = PostMongoRepository(mongoDatabase)
    val unfollowUserAction = unFollowUserAction(unFollowUserHandler(followMongoUserRepository))
    val followUserAction = FollowUserAction(FollowUserHandler(followMongoUserRepository))
    routing {
        get ("/follow") {

            val follows = followMongoUserRepository.findAll()

            call.respond(HttpStatusCode.Created, follows.map{it.toPrimitives()})

        }

        post ("/follow") {
            val body = call.receive<FollowUserCommand>()
            followUserAction.execute(body);
            call.respond(HttpStatusCode.Created, "message" to "ok")
        }
        delete("/follow"){
            val body = call.receive<unFollowUserCommand>()
            if(followMongoUserRepository.findByUser(body.username)!=null){
                unfollowUserAction.execute(body);
                call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
            }

        }
        @Serializable
        data class PostListWrapper(val posts: List<Post>)
        get("/posts/follow/{uuidUser}") {
            val followsUsers = followMongoUserRepository.findAll();
            val Post =postRepository.findPostbyFollow(followsUsers)
            val wrappedResult = PostListWrapper(Post)
            call.respond(HttpStatusCode.OK, wrappedResult)

        }
    }
}