package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commandhandlers.unFollowerUserHandler
import utn.methodology.application.commands.unFollowerUserCommand

class unFollowerUserAction (
    private val handler: unFollowerUserHandler
) {
    fun execute(body: unFollowerUserCommand) {

        body.validate().let {
            handler.handle(body);
        }
    }
}