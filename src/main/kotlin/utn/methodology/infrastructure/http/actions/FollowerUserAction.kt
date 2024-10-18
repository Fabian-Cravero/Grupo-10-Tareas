package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commandhandlers.FollowerUserHandler
import utn.methodology.application.commands.FollowerUserCommand

class FollowerUserAction (private val handler: FollowerUserHandler) {
    fun execute(body: FollowerUserCommand) {

        body.validate().let {
            handler.handle(it)
        }
    }
}