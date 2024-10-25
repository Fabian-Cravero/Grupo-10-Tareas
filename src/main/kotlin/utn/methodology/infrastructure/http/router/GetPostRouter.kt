package utn.methodology.infrastructure.http.router
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.infrastructure.http.actions.GetPostAction
import utn.methodology.infrastructure.http.actions.GetUserAction
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB
import  utn.methodology.application.queryhandlers.FindPostByHandlers

fun Application.GetPostRouter(){
    val mongoDataBase = connectToMongoDB()
    val mongoPostRepository = PostMongoRepository(mongoDataBase)
    val getpostAction = GetPostAction(FindPostByHandlers(mongoPostRepository))
    routing {
       get ("/posts") {

           val order = call.request.queryParameters["order"] ?: "DESC"
           val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
           val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

           if (limit <= 0 || limit > 100) {
               call.respond(HttpStatusCode.BadRequest, "Limit must be between 1 and 100")
               return@get
           }

           if (order.uppercase() !in listOf("ASC", "DESC")) {
               call.respond(HttpStatusCode.BadRequest, "Order must be ASC or DESC")
               return@get
           }

           val posts = mongoPostRepository.findAll()

//           getpostAction.execute(body)

           call.respond(HttpStatusCode.Created, posts.map{it.toPrimitives()})
       }
   }
}