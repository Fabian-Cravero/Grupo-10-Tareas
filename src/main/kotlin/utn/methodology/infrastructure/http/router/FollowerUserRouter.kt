package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.commandhandlers.unFollowerUserHandler
import utn.methodology.application.commands.unFollowerUserCommand
import utn.methodology.infrastructure.http.actions.unFollowerUserAction
import utn.methodology.infrastructure.persistence.connectToMongoDB
import utn.methodology.infrastructure.http.actions.FollowerUserAction
import utn.methodology.application.commandhandlers.FollowerUserHandler
import utn.methodology.application.commands.FollowerUserCommand
import utn.methodology.infrastructure.persistence.FollowMongoRepository

fun Application.FollowerUserRouter(){
    val mongoDatabase = connectToMongoDB()
    val followerMongoUserRepository = FollowMongoRepository(mongoDatabase)
    val unfollowerUserAction = unFollowerUserAction(unFollowerUserHandler(followerMongoUserRepository))
    val followerUserAction = FollowerUserAction(FollowerUserHandler(followerMongoUserRepository))
    routing {
        post ("/follower") {

            val body = call.receive<FollowerUserCommand>()
            followerUserAction.execute(body);
            call.respond(HttpStatusCode.Created, "message" to "ok")
        }

        get ("/follower") {

            val follows = followerMongoUserRepository.findAll()

            call.respond(HttpStatusCode.Created, follows)

        }
        delete("/follower"){
            val body = call.receive<unFollowerUserCommand>()
            if(followerMongoUserRepository.findByUser(body.username)!=null){
                unfollowerUserAction.execute(body);
                call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
            }

        }
    }
}