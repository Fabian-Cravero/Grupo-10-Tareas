package utn.methodology.infrastructure.http.actions

import utn.methodology.application.handlers.FindUserByUsernameHandler
import utn.methodology.application.queries.FindUserByUsernameQuery


class GetUserAction(private val handler: FindUserByUsernameHandler) {

    fun execute(query: FindUserByUsernameQuery): Map<String, String> {
        query
            .validate()
            .let {
                return handler.handle(it)
            }

    }
}