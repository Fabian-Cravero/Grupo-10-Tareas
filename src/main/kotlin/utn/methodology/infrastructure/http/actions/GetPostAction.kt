package utn.methodology.infrastructure.http.actions

import utn.methodology.application.queries.FindPostByIdQuery
import utn.methodology.application.queryhandlers.FindPostByHandlers


class GetPostAction(private val handler : FindPostByHandlers) {

    fun execute(query: FindPostByIdQuery): Map<String, String> {
        query
            .validate()
            .let {
                return handler.handle(it)
            }
    }
}