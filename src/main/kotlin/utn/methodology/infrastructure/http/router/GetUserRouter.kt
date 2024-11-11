package utn.methodology.infrastructure.http.router

import io.ktor.http.*
import utn.methodology.infrastructure.persistence.connectToMongoDB
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.handlers.FindUserByUsernameHandler
import utn.methodology.application.queries.FindUserByUsernameQuery
import utn.methodology.infrastructure.http.actions.GetUserAction
import utn.methodology.infrastructure.persistence.UserMongoRepository

fun Application.GetUserRouter(){
    val mongoDatabase = connectToMongoDB()
    val userMongoUserRepository = UserMongoRepository(mongoDatabase)
    val getUserAction = GetUserAction(FindUserByUsernameHandler(userMongoUserRepository))
    routing {

        get ("/users") {
            val username = call.request.queryParameters.get("username").toString()

            val query = FindUserByUsernameQuery(username)
            val result = getUserAction.execute(query);

            call.respond(HttpStatusCode.Created, result)

        }
    }
}