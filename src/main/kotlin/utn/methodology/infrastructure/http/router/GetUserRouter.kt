package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import utn.methodology.infrastructure.persistence.connectToMongoDB
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.infrastructure.http.actions.GetUserAction
import utn.methodology.infrastructure.persistence.MongoUserRepository

fun Application.GetUserRouter(){
    val mongoDatabase = connectToMongoDB()
    val userMongoUserRepository = MongoUserRepository(mongoDatabase)
    routing {

        post ("/users") {

            val users = userMongoUserRepository.findAll()

            call.respond(HttpStatusCode.OK, users.map{it.toPrimitives()})

        }
    }
}