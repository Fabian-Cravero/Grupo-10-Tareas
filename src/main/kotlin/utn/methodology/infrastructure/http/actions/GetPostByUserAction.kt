package utn.methodology.infrastructure.http.actions

import utn.methodology.application.queries.FindPostByIdQuery
import utn.methodology.application.queryhandlers.FindPostByUserHandler
import utn.methodology.domain.entities.Post
class GetPostByUserAction(private val handler : FindPostByUserHandler) {
    fun execute(query: FindPostByIdQuery): List<Post> {
        query
            .let {
                return handler.handle(it)
            }
    }
}