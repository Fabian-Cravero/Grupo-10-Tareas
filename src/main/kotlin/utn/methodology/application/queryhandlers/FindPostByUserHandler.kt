package utn.methodology.application.queryhandlers
import utn.methodology.infrastructure.persistence.PostMongoRepository
import utn.methodology.application.queries.FindPostByIdQuery
import utn.methodology.domain.entities.Post
class FindPostByUserHandler( private val PostRepository: PostMongoRepository ){
    fun handle(query: FindPostByIdQuery): List<Post> {
        val post = PostRepository.finAllPostByUser(query.id)
        return post
    }
}