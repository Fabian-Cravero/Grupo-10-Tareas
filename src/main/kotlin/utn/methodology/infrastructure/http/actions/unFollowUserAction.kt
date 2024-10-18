package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commands.unFollowUserCommand
import utn.methodology.application.commandhandlers.unFollowUserHandler

class unFollowUserAction(
    private val handler: unFollowUserHandler
) {
    fun execute(body: unFollowUserCommand) {

        body.validate().let {
            handler.handle(body);
        }
    }
}