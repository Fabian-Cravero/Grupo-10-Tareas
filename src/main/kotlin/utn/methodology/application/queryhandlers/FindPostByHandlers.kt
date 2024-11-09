package utn.methodology.application.queryhandlers

import io.ktor.server.plugins.*
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.application.queries.FindPostByIdQuery

class FindPostByHandlers (
    private val PostRepository: PostMongoRepository
) {

    fun handle(query: FindPostByIdQuery): Map<String, String> {

        val post = PostRepository.findOne(query.id)


            ?:  throw NotFoundException("Post with id: ${query.id} not found")


        return post.toPrimitives()
    }
}