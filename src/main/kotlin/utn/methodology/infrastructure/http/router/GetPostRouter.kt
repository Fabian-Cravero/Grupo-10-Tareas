package utn.methodology.infrastructure.http.router
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB

fun Application.GetPostRouter(){
    val mongoDataBase = connectToMongoDB()
    val mongoPostRepository = PostMongoRepository(mongoDataBase)
    routing {
       get ("/posts") {

           val order = call.request.queryParameters["order"] ?: "DESC"
           val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
           val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

           if (limit <= 0 || limit > 100) {
               call.respond(HttpStatusCode.BadRequest, "Limit must be between 1 and 100")
               //return@get
           }

           if (order.uppercase() !in listOf("ASC", "DESC")) {
               call.respond(HttpStatusCode.BadRequest, "Order must be ASC or DESC")
               //return@get
           }

           val posts = mongoPostRepository.findAll(order, limit, offset)


           //val post = mongoPostRepository.findAll()

           call.respond(HttpStatusCode.OK, posts.map{it.toPrimitives()})
       }
   }
}