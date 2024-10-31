package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commands.GetPostCommand
import utn.methodology.application.commands.GetUserCommand
import utn.methodology.application.queries.FindPostByIdQuery
import utn.methodology.application.queryhandlers.FindPostByHandlers
import utn.methodology.domain.entities.Post

class GetPostAction(private val handler : FindPostByHandlers) {

    fun execute(query: FindPostByIdQuery): Map<String, String> {
        query
            .validate()
            .let {
                return handler.handle(it)
            }
    }
}