package utn.methodology.infrastructure.http.router
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.application.queries.FindPostByIdQuery
import utn.methodology.infrastructure.http.actions.GetPostAction
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB
import  utn.methodology.application.queryhandlers.FindPostByHandlers
import utn.methodology.application.queryhandlers.FindPostByUserHandler
import utn.methodology.domain.entities.Post
import utn.methodology.infrastructure.http.actions.GetPostByUserAction
import kotlinx.serialization.Serializable

fun Application.GetPostRouter(){
    val mongoDataBase = connectToMongoDB()
    val mongoPostRepository = PostMongoRepository(mongoDataBase)
    val getpostAction = GetPostAction(FindPostByHandlers(mongoPostRepository))
    val getpostByUserAction= GetPostByUserAction(FindPostByUserHandler(mongoPostRepository))
    routing {
       get ("/posts") {

           val order = call.request.queryParameters["order"] ?: "DESC"
           val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
//           val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

           if (limit <= 0 || limit > 100) {
               call.respond(HttpStatusCode.BadRequest, "Limit must be between 1 and 100")
               return@get
           }

           if (order.uppercase() !in listOf("ASC", "DESC")) {
               call.respond(HttpStatusCode.BadRequest, "Order must be ASC or DESC")
               return@get
           }

           val post = call.request.queryParameters.get("uuid").toString()
           val query = FindPostByIdQuery(post)
           val result = getpostAction.execute(query);

           call.respond(HttpStatusCode.Created, result)
       }


        @Serializable
        data class PostListWrapper(val posts: List<Post>)
        get("/posts/{uuidUser}") {
            val order = call.request.queryParameters["order"] ?: "DESC"
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
//           val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

            if ( limit > 100) {
                call.respond(HttpStatusCode.BadRequest, "Limit must be between 1 and 100")
                return@get
            }

            if (order.uppercase() !in listOf("ASC", "DESC")) {
                call.respond(HttpStatusCode.BadRequest, "Order must be ASC or DESC")
                return@get
            }
            val uuidUser = call.parameters["uuidUser"].toString()
            val query = FindPostByIdQuery(uuidUser)
            val result = getpostByUserAction.execute(query)
            val wrappedResult = PostListWrapper(result)
            call.respond(HttpStatusCode.OK, wrappedResult)
        }
   }
}